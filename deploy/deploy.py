import base64

name_and_files_to_encode = [
    ["GOOGLE_SERVICES_ACCOUNT_JSON", "./deploy/google-service-account.json"],
    ["GOOGLE_SERVICES_JSON", "./composeApp/src/google-services.json"],
    ["KEYSTORE_PROPERTIES", "./keystore/keystore.properties"],
    ["UPLOAD_JKS", "./keystore/upload.jks"]
]

for name_and_file in name_and_files_to_encode:
    encoded = str(base64.b64encode(open(name_and_file[1], "rb").read()))
    encoded = encoded[2:len(encoded) - 1]
    print(f"\n---\n{name_and_file[0]}\n{name_and_file[1]}\n{encoded}\n---")
