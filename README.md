# mower

Just for fun

# Config

If you have Intellij it will find directly the main class (via .run/MowerApplication.run.xml) otherwise you should run
it manually "MowerApplication"

# Environment

you can configure application via env variables (file: application.properties)

Variable | Description |Default value |
--- | --- | --- |
MOWER_CONFIG_PATH | Config file's path |files/config.txt |
MOWER_ELEMENTS_SEPARATOR | Separator between elements for max point/position |SPACE|
MOWER_MIN_X | min x position |0|
MOWER_MIN_Y |min Y position  |0|
MOWER_AUTO_START | Load and execute mowers directly after application start |true|
