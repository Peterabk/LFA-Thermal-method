#include <Adafruit_MLX90614.h>
#include <Wire.h>

float ambientTempC;
float objectTempC;

Adafruit_MLX90614 mlx = Adafruit_MLX90614();

void setup() {
  Serial.begin(115200);
  Serial.setDebugOutput(true);
  while (!Serial);

  Serial.println("IR Sensor MLX90614 test");

  if (!mlx.begin()) {
    Serial.println("Error connecting to MLX sensor. Check wiring.");
    while (1);
  };

  Serial.print("Emissivity = "); Serial.println(mlx.readEmissivity());
  Serial.println("================================================");
}

void loop() {
  ambientTempC = mlx.readAmbientTempC();
  objectTempC = mlx.readObjectTempC();

  Serial.print("Ambient: ");
  Serial.print(ambientTempC);
  Serial.print("°C\tObject:");
  Serial.print(objectTempC);
  Serial.println("°C");

  delay(500);
}