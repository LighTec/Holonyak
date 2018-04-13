#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
#include <avr/power.h>
#endif

// This class uses bytes to speed up data processing.

Adafruit_NeoPixel strip;

void setup() {

#if defined (__AVR_ATtiny85__)
  if (F_CPU == 16000000) clock_prescale_set(clock_div_1);
#endif

  Serial.begin(9600);
  while (Serial.available() < 4) {

  }
  String w = "okay"; // used in case of program jumping, do not remove
  String n = "Nokay"; //same here.
  String init = Serial.readStringUntil('z');
  int pin = hexToDec(init.substring(0, 2));
  int totalPixels = hexToDec(init.substring(2, 5));
  // the final of the 4 hex digits is 4 binary yes/no to options. Unimplemented.

  strip = Adafruit_NeoPixel(totalPixels, pin, NEO_GRB + NEO_KHZ800);
  strip.begin();
  strip.show(); // Initialize all pixels to 'off'
  strip.setPixelColor(strip.numPixels() / 2, strip.Color(255, 0, 0));
  strip.show();
  delay(100);
  strip.setPixelColor(strip.numPixels() / 2, strip.Color(0, 0, 0));
  strip.show();
  while (Serial.available() == 0) {
  }
}

int throwaway;

int pixel;
int red;
int green;
int blue;
int wait;

int secondRed;
int secondGreen;
int secondBlue;
int secondWait;

int thirdRed;
int thirdGreen;
int thirdBlue;

int keyRed;
int keyGreen;
int keyBlue;
int keyWait;

int keyPeek;

int lastCmd = -1;

boolean newCmd;

void loop() {
  //checks if there is a new command to run
  if (Serial.available() > 0) {
    while (Serial.available() < 7) {
    }
    throwaway = Serial.read();
    lastCmd = Serial.read();
    newCmd = true;
    keyRed = Serial.read();
    keyGreen = Serial.read();
    keyBlue = Serial.read();
    keyWait = Serial.read();
    keyWait = (keyWait * 256) + Serial.read();
  }
  switch (lastCmd) {
    case 1:
      if (newCmd) {
        newCmd = false;
        CpuMemUpdateable();
      }
      break;
    case 2:
      if (newCmd) {
        newCmd = false;
        singleColorUpdateableWipe();
      }
      break;
    case 4:         // Set entire strip a color, case 3 used to be this but without a wait command. Case 1 also did this, so it was removed.
      if (newCmd) {
        newCmd = false;
        while (Serial.available() < 5) {
        }
        red = Serial.read();
        green = Serial.read();
        blue = Serial.read();
        wait = Serial.read();
        wait = (wait * 256) + Serial.read();
      }
      if (wait == 0) {
        colorWipeNoDelay(strip.Color(red, green, blue));
      } else {
        colorWipe(strip.Color(red, green, blue), wait);
      }
      break;
    case 5:            //"theatre chase", like this:  0__0__ -> _0__0_ -> __0__0
      if (newCmd) {
        while (Serial.available() < 11) {
        }
        red = Serial.read();
        green = Serial.read();
        blue = Serial.read();

        secondRed = Serial.read();
        secondGreen = Serial.read();
        secondBlue = Serial.read();

        thirdRed = Serial.read();
        thirdGreen = Serial.read();
        thirdBlue = Serial.read();

        wait = Serial.read();
        wait = (wait * 256) + Serial.read();
        newCmd = false;
      }
      TheaterChase(strip.Color(red, green, blue), strip.Color(secondRed, secondGreen, secondBlue), strip.Color(thirdRed, thirdGreen, thirdBlue));
      break;
    case 6:            // cycles through the rainbow, tries to make the strip one
      if (newCmd) {
        newCmd = false;
        while (Serial.available() < 2) {
        }
        wait = Serial.read(); //color at a time
        wait = (wait * 256) + Serial.read();
      }
      rainbow();
      break;
    case 7:            // cycles through the rainbow, with the entire spectrum on
      //the strip from beginning to end.
      if (newCmd) {
        newCmd = false;
        while (Serial.available() < 2) {
        }
        wait = Serial.read(); //color at a time
        wait = (wait * 256) + Serial.read();
      }
      rainbowCycle();
      break;
    case 8:            //"theatre chase" and rainbow combined.
      if (newCmd) {
        newCmd = false;
        while (Serial.available() < 2) {
        }
        wait = Serial.read(); //color at a time
        wait = (wait * 256) + Serial.read();
      }
      theaterChaseRainbow();
      break;
    case 9:
      if (newCmd) {
        newCmd = false;
      }
      setFrame();
      break;
    case 255:
      strip.setPixelColor(strip.numPixels() - 1, strip.Color(50, 0, 0));
      strip.setPixelColor(0, strip.Color(50, 0, 0));
      strip.show();
      delay(100);
      strip.setPixelColor(strip.numPixels() - 1, strip.Color(0, 50, 0));
      strip.setPixelColor(0, strip.Color(0, 50, 0));
      strip.show();
      delay(100);
      strip.setPixelColor(strip.numPixels() - 1, strip.Color(0, 0, 50));
      strip.setPixelColor(0, strip.Color(0, 0, 50));
      strip.show();
      delay(100);

      while (Serial.available() > 0) {
        int clearBuffer = Serial.read();
      }
      lastCmd = -1;
      newCmd = true;
      wait = 0;
      colorWipe(strip.Color(0, 0, 0), 0);
      break;
  }
}

void CpuMemUpdateable() {
  int halfWay = strip.numPixels() / 2; // the end point of block 1
  int cpuLoad = 0;
  int memLoad = 0;
  while (true) {
    while (Serial.available() == 0) {
    }
    int localCommand = Serial.peek();
    if (localCommand == 255) {
      break;
    } else if (localCommand == 100) {
      throwaway = Serial.read();
      while (Serial.available() < 4) {
      }
      cpuLoad = Serial.read();
      cpuLoad = (cpuLoad * 256) + Serial.read();
      memLoad = Serial.read();
      memLoad = (memLoad * 256) + Serial.read();

      red = heatPaletteRed(cpuLoad);
      green = heatPaletteGreen(cpuLoad);
      blue = heatPaletteBlue(cpuLoad);
      secondRed = heatPaletteRed(memLoad);
      secondGreen = heatPaletteGreen(memLoad);
      secondBlue = heatPaletteBlue(memLoad);
      for (uint16_t i = 0; i < (halfWay - 1); i++) {
        strip.setPixelColor(i, strip.Color(red, green, blue));
      }
      for (uint16_t i = halfWay; i < strip.numPixels(); i++) {
        strip.setPixelColor(i, strip.Color(secondRed, secondGreen, secondBlue));
      }
      strip.show();
    } else {
      throwaway = Serial.read();
      keyFlash();
    }
  }
}

void singleColorUpdateableWipe() {
  while (true) {
    while (Serial.available() == 0) {
    }
    int localCommand = Serial.peek();
    if (localCommand == 255) {
      break;
    } else if (localCommand == 100) {
      throwaway = Serial.read();
      while (Serial.available() < 3) {
      }
      red = Serial.read();
      green = Serial.read();
      blue = Serial.read();
      for (uint16_t i = 0; i < strip.numPixels(); i++) {
        strip.setPixelColor(i, strip.Color(red, green, blue));
      }
      strip.show();
    } else {
      throwaway = Serial.read();
      strip.show();
      keyFlash();
    }
  }
}

void colorWipe(uint32_t c, int localDelay) {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    if (Serial.available() > 0) {
      keyPeek = Serial.peek();
      if (keyPeek == 255) {
        break;
      } else {
        throwaway = Serial.read();
        keyFlash();
      }
    }
    strip.setPixelColor(i, c);
    strip.show();
    delay(localDelay);
  }
  while (true) {
    while (Serial.available() == 0) {
    }
    keyPeek = Serial.peek();
    if (keyPeek == 255) {
      break;
    } else {
      throwaway = Serial.read();
      keyFlash();
      for (uint16_t i = 0; i < strip.numPixels(); i++) {
        strip.setPixelColor(i, c);
      }
      strip.show();
    }
  }
}

void colorWipeNoDelay(uint32_t c) {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, c);
  }
  strip.show();
  while (true) {
    while (Serial.available() == 0) {
    }
    keyPeek = Serial.peek();
    if (keyPeek == 255) {
      break;
    } else {
      throwaway = Serial.read();
      keyFlash();
      for (uint16_t i = 0; i < strip.numPixels(); i++) {
        strip.setPixelColor(i, c);
      }
      strip.show();
    }
  }
}

//Runs a rainbow along the strip
void rainbow() {
  uint16_t i, j;

  for (j = 0; j < 256; j++) {
    for (i = 0; i < strip.numPixels(); i++) {

      strip.setPixelColor(i, Wheel((i + j) & 255));
    }
    strip.show();
    if (Serial.available() > 0) {
      keyPeek = Serial.peek();
      if (keyPeek == 255) {
        break;
      } else {
        throwaway = Serial.read();
        keyFlash();
      }
    }
    delay(wait);
  }
  strip.show();
}

// Slightly different, this makes the rainbow equally distributed throughout
void rainbowCycle() {
  uint16_t i, j;

  for (j = 0; j < 256 * 5; j++) { // 5 cycles of all colors on wheel
    for (i = 0; i < strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    if (Serial.available() > 0) {
      keyPeek = Serial.peek();
      if (keyPeek == 255) {
        break;
      } else {
        throwaway = Serial.read();
        keyFlash();
      }
    }
    delay(wait);
  }
}

//Theatre-style crawling lights.
void TheaterChase(uint32_t c, uint32_t c2, uint32_t c3) {
  for (int j = 0; j < 10; j++) { //do 10 cycles of chasing
    for (int q = 0; q < 3; q++) {
      for (uint16_t i = 0; i < strip.numPixels(); i = i + 3) {
        strip.setPixelColor(i + q, c);  //turn every third pixel on
        if (i + q - 3 == 0) {
          strip.setPixelColor(0, c);
        }
      }
      for (uint16_t i = 1; i < strip.numPixels(); i = i + 3) {
        strip.setPixelColor(i + q, c2);  //turn every third pixel on
        if (i + q - 3 == 0) {
          strip.setPixelColor(0, c2);
        }
      }
      for (uint16_t i = 2; i < strip.numPixels(); i = i + 3) {
        strip.setPixelColor(i + q, c3);  //turn every third pixel on
        if (i + q - 3 == 0) {
          strip.setPixelColor(0, c3);
        } else if (i + q - 4 == 0) {
          strip.setPixelColor(1, c3);
        }
      }
      strip.show();
      delay(wait);
      if (Serial.available() > 0) {
        keyPeek = Serial.peek();
        if (keyPeek == 255) {
          break;
        } else {
          throwaway = Serial.read();
          keyFlash();
        }
      }
    }
  }
}

//Theatre-style crawling lights with rainbow effect
void theaterChaseRainbow() {
  for (int j = 0; j < 256; j++) {   // cycle all 256 colors in the wheel
    for (int q = 0; q < 3; q++) {
      for (uint16_t i = 0; i < strip.numPixels(); i = i + 3) {
        strip.setPixelColor(i + q, Wheel( (i + j) % 255)); //turn every third pixel on
      }
      strip.show();
      if (Serial.available() > 0) {
        keyPeek = Serial.peek();
        if (keyPeek == 255) {
          break;
        } else {
          throwaway = Serial.read();
          keyFlash();
        }
      }

      delay(wait);

      for (uint16_t i = 0; i < strip.numPixels(); i = i + 3) {
        strip.setPixelColor(i + q, 0);      //turn every third pixel off
      }
    }
  }
}

void keyFlash() {
  for (int i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, strip.Color(keyRed, keyGreen, keyBlue));
  }
  strip.show();
  delay(keyWait);
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if (WheelPos < 85) {
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if (WheelPos < 170) {
    WheelPos -= 85;
    return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}

unsigned int hexToDec(String hexString) {

  unsigned int decValue = 0;
  int nextInt;

  for (int i = 0; i < hexString.length(); i++) {

    nextInt = int(hexString.charAt(i));
    if (nextInt >= 48 && nextInt <= 57) nextInt = map(nextInt, 48, 57, 0, 9);
    if (nextInt >= 65 && nextInt <= 70) nextInt = map(nextInt, 65, 70, 10, 15);
    nextInt = constrain(nextInt, 0, 15);

    decValue = (decValue * 16) + nextInt;
  }

  return decValue;
}

int heatPaletteRed(int pal) {
  if (pal < 511) {
    return 0;
  } else if (pal > 767) {
    return 255;
  } else {
    return (pal - 511);
  }
}

int heatPaletteGreen(int pal) {
  if (pal < 256) {
    return pal;
  } else if (pal < 768) {
    return 255;
  } else {
    return 1024 - pal;
  }
}

int heatPaletteBlue(int pal) {
  if (pal < 256) {
    return 255;
  } else if ( pal > 511) {
    return 0;
  } else {
    return (512 - pal);
  }
}

//sets certain pixels various color levels.
void setPixel() {
  while (Serial.available() < 5) {  // unused code
  }
  pixel = Serial.read();
  pixel = (pixel * 256) + Serial.read();
  red = Serial.read();
  green = Serial.read();
  blue = Serial.read();

  strip.setPixelColor(pixel, red, green, blue);
  strip.show();
}

//sets all pixels discrete rgb values.
void setFrame() {
  while (true) {
    while (Serial.available() == 0) {
    }
    keyPeek = Serial.peek();
    if (keyPeek == 255) {
      break;
    }
    throwaway = Serial.read();
    for (int i = 0; i < strip.numPixels(); i++) {
      while (Serial.available() < 3) {
      }
      red = Serial.read();
      green = Serial.read();
      blue = Serial.read();

      strip.setPixelColor(i, red, green, blue);
    }
    strip.show();
  }
}
