#include <Keypad.h>
#include <LiquidCrystal_I2C.h>
#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

#define SOM 33

LiquidCrystal_I2C lcd(0X27, 16, 2);

const uint8_t ROWS = 4;
const uint8_t COLS = 4;

const char* SSID = "Wokwi-GUEST"; 
const char* PASSWORD = "";

HTTPClient http;

String api = "https://dcea-177-93-150-55.ngrok-free.app";
String logAcesso = "https://api.thingspeak.com/update?api_key=QYVMO3H6J77T93IK&field2=";

char keys[ROWS][COLS] = {
  { '1', '2', '3', 'A' },
  { '4', '5', '6', 'B' },
  { '7', '8', '9', 'C' },
  { '*', '0', '#', 'D' }
};
uint8_t colPins[COLS] = { 18, 5, 17, 16 }; 
uint8_t rowPins[ROWS] = { 23, 22, 21, 19 };
Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);


void setup() {
  Serial.begin(115200);

  WiFi.begin(SSID, PASSWORD);

  Wire.begin(26,27);
  lcd.init();
  lcd.backlight();

  pinMode(SOM, OUTPUT);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    lcd.print(".");
  }

  lcd.clear();

  lcd.print("Wifi Connected");
  delay(2000);
  lcd.clear();

  http.begin(api + "/helloworld"); 

  int httpCode = http.GET();
  if (httpCode > 0) {
    lcd.print("API Connected");
    Serial.println("Resposta da API: " + http.getString()); 
  } else {
    lcd.print("Erro: " + String(httpCode));
    Serial.println("Erro ao conectar à API. Código: " + String(httpCode));
  }

  delay(4000);
  lcd.clear();

  lcd.setCursor(4,0);
  lcd.print("Digite");
  lcd.setCursor(3,1);
  lcd.print("sua senha:");
}

String senha = "";

void conferir(){
 

  http.begin(api + "/credenciais/entrar/" + senha); 

  int httpCode = http.POST("");

  if(httpCode > 0){
    if(http.getString() == "Senha valida"){
      tone(SOM, 252, 100); 
      lcd.print("Liberado");
      noTone(SOM);
      
      http.begin(logAcesso + "&field2=" + 1); 
    } else {
      Serial.println(http.getString());
      tone(SOM, 252, 100);
      lcd.print("Senha Errada");
      noTone(SOM);
    }
  } else {
    lcd.print("Erro: "+String(httpCode));
  }
}

void loop() {
  char key = keypad.getKey();
  
  if (key != NO_KEY) {
    if(key != '*'){
      senha += key;
    } else {
      lcd.clear();
      conferir();
      delay(2000);
      lcd.clear();

      lcd.setCursor(4,0);
      lcd.print("Digite");
      lcd.setCursor(3,1);
      lcd.print("sua senha:");

      senha = "";
    }
  }
}
