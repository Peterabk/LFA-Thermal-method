#include<WiFi.h>
#include<Firebase_ESP_Client.h>
#include "addons/TokenHelper.h"
#include "addons/RTDBHelper.h"

//connect to your wifi
#define WIFI_SSID "Peterphone"
#define WIFI_PASSWORD "123456789"

//specific to your databse
#define API_KEY "AIzaSyCTVro-3T8AxCQgbev7SIOn6Loe1qBGW24"
#define DATABASE_URL "https://syncova-388a3-default-rtdb.firebaseio.com/"

//LED pins
#define LED1_PIN 12
#define LED2_PIN 14

#define PWMChannel 0
const int frequency = 5000;
const int resolution = 8;
int pwmValue = 0;
bool ledstatus = false;

//this will handle the data when there's a change on a specific node path
FirebaseData fbdo;

//needed for authentication
FirebaseAuth auth;

//needed for configuration
FirebaseConfig config;

unsigned long sendDataPrevMillis = 0;
bool SignupOK = false;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(LED2_PIN, OUTPUT);
  ledcSetup(PWMChannel, frequency, resolution);
  ledcAttachPin(LED1_PIN, PWMChannel);

  //Connect to Wifi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wifi");
  while(WiFi.status() != WL_CONNECTED)
  {
    Serial.print("."); delay(300);
    if(WiFi.status() != WL_NO_SSID_AVAIL)Serial.println("Unable to find ssid");
    if(WiFi.status() != WL_CONNECT_FAILED)Serial.println("connection failed");
    if(WiFi.status() != WL_CONNECTION_LOST)Serial.println("Connection lost");
    
  }
  delay(3000);
  Serial.println();
  Serial.println("Wifi to WIFI");
  Serial.print("Wifi IP: " + WiFi.localIP());

  config.api_key = API_KEY;
  config.database_url = DATABASE_URL;

  if(Firebase.signUp(&config, &auth, "", ""))
  {
    Serial.print("Signup OK");
    SignupOK = true;
  } 
  else{
    Serial.print("Firebase no sign up");
  }

  config.token_status_callback = tokenStatusCallback;
  Firebase.begin(&config, &auth);
  Firebase.reconnectWiFi(true);

}

void loop() {
  // put your main code here, to run repeatedly:
  //Here we are sending infromation to the Firebase database
  if(Firebase.ready() && SignupOK && (millis() - sendDataPrevMillis >5000 || sendDataPrevMillis ==0))
  {
    //here we are restarting the countdown to send data to databse since we need to do it each 5s and not each time we loop
    sendDataPrevMillis = millis();
    //Sensor/led1_data will be autotmatically create if the section was not already present in the databse
    if(Firebase.RTDB.setInt(&fbdo, "Sensor/led1_data",digitalRead(LED1_PIN))){
      Serial.println(digitalRead(LED1_PIN));
      Serial.println("-Successfully saved to: " + fbdo.dataPath());
      Serial.println("(" + fbdo.dataType() + ")");
    }
    else
    {
      Serial.println("FAILED TO SEND" + fbdo.errorReason());
    }

    if(Firebase.RTDB.getInt(&fbdo, "LED/analog")){
      if(fbdo.dataType() == "int"){
        pwmValue = fbdo.intData();
        Serial.println("-Successfully reading" + fbdo.dataPath() + " : "+ pwmValue);
        ledcWrite(PWMChannel, pwmValue);
      }
      else
      {
        Serial.println("FAILED : "+ fbdo.errorReason());
      }
    }
    else
    {
      Serial.println("FAILED TO SEND" + fbdo.errorReason());
    }

    if(Firebase.RTDB.getInt(&fbdo, "LED/digital")){
      if(fbdo.dataType() == "boolean"){
        ledstatus = fbdo.boolData();
        Serial.println("-Successfully reading" + fbdo.dataPath() + " : "+ ledstatus);
        digitalWrite(LED2_PIN, ledstatus);
      }
      else
      {
        Serial.println("FAILED : "+ fbdo.errorReason());
      }
    }
    else
    {
      Serial.println("FAILED TO SEND" + fbdo.errorReason());
    }
  }

  
}

