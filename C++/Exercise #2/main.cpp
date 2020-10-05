#include <iostream>
#include <bits/stdc++.h>

using namespace std;
void sort(int a[],int size);


int main() {
    int arr[] = {-12,3,-12,4,1,1,-12,1,-1,1,2,3,4,2,3,-12};
    int m = sizeof(arr)/sizeof(arr[0]);
    int arr2[m];
    int counter = 1;



        for (int j = 0; j < m; j++) {
            arr2[j] = arr[j];
        }

        sort(arr2,m);

        cout << "  N      Count\n";

        for(int i = 0; i < m; i++) {
                if(arr2[i] == arr2[i+1])
                counter++;
                else {
                    cout << arr2[i] << "        " << counter << "\n";
                    counter = 1;
                }
        }

    return 0;
}

void sort(int a[],int size) {
    int temp;

    for(int i=0;i<=size;i++) {
        for(int j=0;j<=size-i;j++) {
            if(a[j]>a[j+1]) {
                temp=a[j];
                a[j]=a[j+1];
                a[j+1]=temp;
            }
        }
    }
 }

