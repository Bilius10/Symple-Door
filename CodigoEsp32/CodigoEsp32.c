#include <Keypad.h>
#include <LiquidCrystal_I2C.h>
#include <WiFi.h>

#define SOM 33

LiquidCrystal_I2C lcd(0X27, 16, 2);

const uint8_t ROWS = 4;
const uint8_t COLS = 4;

char ssid[] = "Wokwi-GUEST";
char pass[] = "";

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

  Wire.begin(26,27);
  lcd.init();
  lcd.backlight();

  pinMode(SOM, OUTPUT);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    lcd.print(".");
  }
  lcd.clear();

  lcd.print("Conectado ao wifi");
  delay(2000);
  lcd.clear();

  lcd.setCursor(4,0);
  lcd.print("Digite");
  lcd.setCursor(3,1);
  lcd.print("sua senha:");
}

String senhaCorreta = "AB564";
String senha ="";

void conferir(){

    if(senhaCorreta == senha){
      tone(SOM, 252, 100); 
      lcd.print("Liberado");
      noTone(SOM);

    }else{
      tone(SOM, 252, 100);
      lcd.print("Senha Errada");
      noTone(SOM);
    }
}

void loop() {
  char key = keypad.getKey();
  
  if (key != NO_KEY) {
    if(key != '*'){
      senha += key;
    }else{
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