import sys


def funcion_principal (pesos: list , intercambios: int, j: int):
    n = len(pesos)
    dp = [None] * (intercambios+1)
    #caso base cuando no se tienen intercambios
    dp[0] = pesos
    #si solo hay un intercambio, solo podemos ver sin en la frontera vale la pena intercambiar
    pesos_m1 = pesos.copy()
    if pesos[j-1] > pesos[j]:
        swap(pesos_m1, j, j-1, j)
    dp[1] = pesos_m1
    #caso recursivo, m > 1
    
    for m in range(2, intercambios+1):
        
        #asumimos que lo mejor es lo del anterior para iniciar
        mejor_arreglo = dp[m-1]
        mejor_sum = funcion_suma_j_primeros(mejor_arreglo, j)
        
        #revisar casos extremos propios
        propios = encontrar_mejor_swap(pesos, m, j)
        sum_propios = funcion_suma_j_primeros(propios, j)
        
        if (sum_propios< mejor_sum):
            mejor_arreglo = propios
            mejor_sum = sum_propios       
        
        
        for l in range (1, m-1):
            
            extremos_m = dp[l]
            mejor_extremos_m = encontrar_mejor_swap(extremos_m, m-l, j)
            sum_mejor_extremos_m = funcion_suma_j_primeros(mejor_extremos_m, j)
            
            if (sum_mejor_extremos_m < mejor_sum):
                mejor_sum = sum_mejor_extremos_m
                mejor_arreglo = mejor_extremos_m
            
        dp[m] = mejor_arreglo
    
    return funcion_suma_j_primeros(dp[intercambios], j)        
        
    
def funcion_suma_j_primeros (lista: list, j:int):
    sum = 0
    for i in range(0, j):
        sum+= lista[i]
    return sum

def swap(lista: list, j: int, pos_mayor: int, pos_menor:int):
    
    if pos_mayor < j-1:
        
        while pos_mayor < j-1:
            
            mayor_value = lista[pos_mayor]
            lista[pos_mayor] = lista[pos_mayor+1]
            lista[pos_mayor+1] = mayor_value
            pos_mayor += 1
    
    if pos_menor > j:
        
        while pos_menor >j:
            
            menor_value = lista[pos_menor]
            lista[pos_menor] = lista[pos_menor-1]
            lista[pos_menor-1] = menor_value
            pos_menor -= 1
    
    mayor_value = lista[j-1]
    lista[j-1] = lista[j]
    lista[j] = mayor_value
    
    return lista

def encontrar_mejor_swap(lista: list, m: int, j: int):
    
    mayor_diferencia = 0
    mejor_posicion_swap = -1
    if (m > j):
        for i in range(0, j):
            #Revisamos que el extremo a intercambiar este en la lista
            if i + m < len(lista):
                diferencia_actual = lista[i] - lista[i+m]
                if diferencia_actual > mayor_diferencia:
                    mayor_diferencia = diferencia_actual
                    mejor_posicion_swap = i      
    else:
        for i in range(j-m, j):
            if i + m < len(lista):
                diferencia_actual = lista[i] - lista[i+m]
                if diferencia_actual > mayor_diferencia:
                    mayor_diferencia = diferencia_actual
                    mejor_posicion_swap = i      
    if mejor_posicion_swap > -1:
        nueva_lista = lista.copy()
        swap(nueva_lista, j, mejor_posicion_swap, mejor_posicion_swap+m)
        return nueva_lista
    else:
        return lista
    


def main():
    linea = sys.stdin.readline()
    ncasos = int(linea)
    linea = sys.stdin.readline()
    
    for i in range(0, ncasos):
        numeros = [int(num) for num in linea.split()]
        n = numeros[0]
        j = numeros[1]
        m = numeros[2]
        pesos = numeros[3:3+n]
        respuesta = funcion_principal(pesos, m, j)
        print(str(respuesta))
        linea = sys.stdin.readline()
        
    
main()
