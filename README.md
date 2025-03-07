# DevTITANS 06 - HandsOn Final - Equipe 05

Bem-vindo ao repositório da Equipe 05 no DevTITANS!

- [Contribuidores](#contribuidores)
- [Recursos](#recursos)
- [Uso](#uso)
- [Contato](#contato)

## Contribuidores

<img src="https://github.com/user-attachments/assets/63940172-0bda-4b78-891a-9230b7a64bfc" width="180" >
<img src="https://github.com/user-attachments/assets/1188205f-0740-4a13-9106-fbbfe7157e20" width="180" >
<img src="https://github.com/user-attachments/assets/086b4661-5d9d-42fb-a264-49ea34a91948" width="180" >
<img src="https://github.com/user-attachments/assets/bf121632-4689-4dd5-9def-33471e36d362" width="180" >
<img src="https://github.com/user-attachments/assets/16f8dbcf-65cd-4908-bc0e-4361bf7788a9" width="180" >  

<br/>
<br/>
<br/>
    
- **Antonio Fernandes:** Firmware e Driver
- **Cristiano Goes:** Driver
- **Emidio Aranha:** Organização, Protótipo, firmware
- **Igor Carvalho:** Organização e Driver
- **Luiz Sena:** Driver e Port G100

## Recursos

- Smartphone com Android 14
- Microcontrolador Esp32
- Sensor de Gás MQ-9 Monóxido de Carbono e Combustíveis
- Sensor SDS011 de qualidade do ar
- Protoboard, Jumpers, Cabo usb


## Uso

### Protótipo

* Conectar o SDS011 aos pinos 16 (RX), 17 (TX), ao Vin e GND, e o MQ9 aos pinos 26 (Digital), 32 (Analógico), VIn e GND;
* Carregar o código dentro da pasta MQ9preheating no esp32 e esperar 48 horas para a calibração da resistencia da carga. Salvar o valor normalizado para adicionar no elemento R0 do código de AirQuality.ino;
* Carregar o código da pasta AirQuality usando o valor R0 para captar os valores de qualidade do ar do ambiente. 

{Instruções para reproduzir as alterações no AOSP}

## Contato

Para perguntas, sugestões ou feedback, entre em contato com o mantenedor do projeto em [emidioaranha21@gmail.com](mailto:emidioaranha21@gmail.com).
