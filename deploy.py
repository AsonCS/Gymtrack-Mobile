import base64

files_to_encode = [
    "./composeApp/src/google-services.json",
    "./keystore/keystore.properties",
    "./keystore/upload.jks"
]

for file in files_to_encode:
    encoded = str(base64.b64encode(open(file, "rb").read()))
    print(file + " = " + encoded[2:len(encoded) - 1])

