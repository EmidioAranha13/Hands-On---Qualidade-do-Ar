#include <HardwareSerial.h>

// SDS011 serial connection
#define SDS_RX 16 // Ajuste para o pino RX correto da sua placa
#define SDS_TX 17 // Ajuste para o pino TX correto da sua placa
#define LED 34 // Led de alerta
#define DO 26 // Digital
#define AO 32 // Analógico


HardwareSerial sds(2); // Use Serial1 para o SDS011 (no ESP32, Serial1 é customizável)

// Estrutura para armazenar os valores do sensor
struct AirQualityData {
  int pm2_5;
  int pm10;
  bool isValid; // Indica se os dados são válidos
};

struct MQ9 {
  int sensor_volt;
  int RS_gas;
  int ratio; 
};

struct AllData {
  AirQualityData data1;
  MQ9 data2;
};

int alert = 0;
float R0 = 0.91;

// Função para ler os dados da serial
AirQualityData readSDS011() {
  AirQualityData data = {0, 0, false};
  
  // Aguarda o byte inicial 0xAA
  while (sds.available() && sds.read() != 0xAA) { }

  byte buffer[10];
  buffer[0] = 0xAA;

  // Verifica se há pelo menos 9 bytes disponíveis para leitura
  if (sds.available() >= 9) {
    sds.readBytes(&buffer[1], 9);

    // Verifica se o último byte é o byte de término correto (0xAB)
    if (buffer[9] == 0xAB) {
      int pm25int = (buffer[3] << 8) | buffer[2];
      int pm10int = (buffer[5] << 8) | buffer[4];
      data.pm2_5 = pm25int / 10.0;
      data.pm10 = pm10int / 10.0;
      //data.pm2_5 = pm25int;
      //data.pm10 = pm10int;
      data.isValid = true;
    }
  }
  return data;
}

// Função para enviar comandos ao SDS011
void writeToSerial(const byte* command, size_t length) {
  sds.write(command, length);
}

//função para ler os dados do MQ9
MQ9 readMq9() {
  MQ9 data = {0, 0, 0};
  int sensorValue = analogRead(AO);  
  float volt;  
  float gas;   //
  float ratio;
  volt = ((float)sensorValue/1024) * 5.0; //voltagem lida do sensor
  gas = (5.0 - volt) / volt;              //resistencia do sensor 
  ratio = gas / R0;

  //multiplicar por 100 quando mandar
  data.sensor_volt = volt * 100;
  data.RS_gas = gas * 100;
  data.ratio = ratio * 100; 

  return data;
}

void setup() {
  // Inicializa a comunicação Serial com o computador
  Serial.begin(9600);
  delay(100);
  Serial.println("Initializing SDS011 Air Quality Monitor...");
  // Inicializa a comunicação HardwareSerial com o SDS011
  sds.begin(9600, SERIAL_8N1, SDS_RX, SDS_TX);
  // Exemplo: enviar comando para colocar o sensor no modo de espera
  byte sleepCommand[] = {0xAA, 0xB4, 0x06, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0x05, 0xAB};
  writeToSerial(sleepCommand, sizeof(sleepCommand));

  pinMode(DO, INPUT);
}

void loop() {

  if(Serial.available() > 0) {
    String cmd = Serial.readString();
    AirQualityData data1 = readSDS011();
    MQ9 data2 = readMq9();
    AllData result;
    //alert = digitalRead(DO);
  
    if (data1.isValid) {
      Serial.println("\n\n");
      Serial.println("---Data1---");
      Serial.print("PM2.5: ");
      Serial.print(data1.pm2_5, 2);
      Serial.print(" ug/m3  ");
      Serial.print("PM10: ");
      Serial.print(data1.pm10, 2);
      Serial.println(" ug/m3");
      Serial.println("\n\n");
    } else {
      Serial.println("Failed to read valid data from SDS011.");
    }

    Serial.println("---Data2---");
    Serial.print("sensor_volt: ");
    Serial.print(data2.sensor_volt);
    Serial.print(", RS_gas: ");
    Serial.print(data2.RS_gas);
    Serial.print(", ratio: ");
    Serial.print(data2.ratio);

    /*
    //(RES struct /n)
    if(data1.isValid) {
      result.data1 = data1;
      //Serial.write((byte*)&data, sizeof(data));
    } else {
      result.data1 = {0,0,false}; 
    }
  
    result.data2 = data2;
  
    Serial.print("RES ");
    Serial.write((byte*)&result, sizeof(result));
    Serial.print(" \n");
    */
  
    /*if(alert==1) digitalWrite(LED, HIGH);
    else if(alert == 0) digitalWrite(LED, lOW);*/
  }
  
  delay(1000);
}
