void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(23, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0) {
    String cmd = Serial.readString();
    if(cmd == "SET 10\n") {
       digitalWrite(23, HIGH);
    }
  }
}