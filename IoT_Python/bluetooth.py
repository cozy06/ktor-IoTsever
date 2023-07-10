import sys
# from bluetooth import *

# 데이터 수집 및 처리 함수 예시
def toUpper(data):
    # 데이터 처리 작업 수행
    upstr = data.upper()
    return upstr

# 데이터 입력 받기
read = sys.argv[1:]

address = read[0]
data = read[1]

print(f"Send {data} to {address}")

# try:
#     socket = BluetoothSocket(RFCOMM)
#     socket.connect((address, 1))
#     print("bluetooth connected!")
#     print(f"Send {data} to {address}")
#     socket.send(data)
#
#     print("END")
#     socket.close()
# except:
#     print("error")