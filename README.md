# Holonyak
2017 Capstone project-- Razer Chroma for an Arduino and RBG strips.
Version 1! Should work with any computer, and Arduino. 
Works with LED chips using WS2812, WS2811 and SK6812 drivers, like the Adafruit
Neopixel range of products

However, it is only tested on a Arduino Uno, and an Adafruit 8X8 Neopixel Panel.

You will also need to upload the provided .ino file to your arduino. To do so,
I'd recommend the [Arduino IDE](https://www.arduino.cc/en/Main/Software).

Current patterns implemented (All patterns can detect key presses):
- Color Fill
- Theater Chase (support to choose all 3 positions)
- Rainbow
- Rainbow Cycle
- Theater Chase: Rainbow Edition
- Hex Clock
- Ram / CPU usage

Also has a button that "debugs" the arduino IO by sending it tons of 255 values, 
which should trigger it to clear its buffer, and wait for further input.


Possible future patterns; (no promises, anything with a (?) is unlikely)
- Matrix-style falling effect
- Audio visualization(?)
- Wave (1-8 Colors moving in a direction at a specified speed)
- Spectrum Cycle
- RNG button for colors and delays
- Ambience (1-8 colors fading in and out)
- CPU temperature (?)
- GPU temperature (?)
- Usage of input on analog and digital IO on the arduino to do things (?)
