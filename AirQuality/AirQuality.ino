#include <HardwareSerial.h>

// SDS011 serial connection
#define SDS_RX 16 // Ajuste para o pino RX correto da sua placa
#define SDS_TX 17 // Ajuste para o pino TX correto da sua placa

HardwareSerial sds(1); // Use Serial1 para o SDS011 (no ESP32, Serial1 é customizável)

// Estrutura para armazenar os valores do sensor
struct AirQualityData {
  float pm2_5;
  float pm10;
  bool isValid; // Indica se os dados são válidos
};

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
      data.isValid = true;
    }
  }
  return data;
}

// Função para enviar comandos ao SDS011
void writeToSerial(const byte* command, size_t length) {
  sds.write(command, length);
}

void setup() {
  // Inicializa a comunicação Serial com o computador
  Serial.begin(115200);
  delay(100);
  Serial.println("Initializing SDS011 Air Quality Monitor...");

  // Inicializa a comunicação HardwareSerial com o SDS011
  sds.begin(9600, SERIAL_8N1, SDS_RX, SDS_TX);

  // Exemplo: enviar comando para colocar o sensor no modo de espera
  byte sleepCommand[] = {0xAA, 0xB4, 0x06, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0x05, 0xAB};
  writeToSerial(sleepCommand, sizeof(sleepCommand));
}

void loop() {
  AirQualityData data = readSDS011();
  
  if (data.isValid) {
    Serial.print("PM2.5: ");
    Serial.print(data.pm2_5, 2);
    Serial.print(" ug/m3  ");
    Serial.print("PM10: ");
    Serial.print(data.pm10, 2);
    Serial.println(" ug/m3");
  } else {
    Serial.println("Failed to read valid data from SDS011.");
  }
  
  delay(1000);
}

