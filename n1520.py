#내리막길

fiboData = [0 for i in range(100)]

def fibo(n):
    global fiboData

    if n <= 2:
        fiboData[n] = 1
        return 1
    if fiboData[n] == 0:
        fiboData = fibo(n - 1) + fibo(n - 2)
    return fiboData[n]

n = int(input())
print(fibo(n))