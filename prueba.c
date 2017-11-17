#include<stdio.h>
#include<stadlib.h>

int main(){
	int a, b, suma;

	printf("Ingrese un valor entero: ");
	scanf("%d", &a);

	printf("Ingrese un otro valor entero: ");
	scanf("%d", &b);

	suma = a + b;

	printf("\nLa suma de %d y %d es %d\n\n", a, b, suma);

}
