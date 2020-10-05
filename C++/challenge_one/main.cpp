#include <iostream>
#include <array>

using namespace std;

bool sum_func(int arr[], int k);

int main()
{
    int arr[] = {10,15,3,7};
    bool result = sum_func(arr,17);
    cout << result << endl;
    return 0;
}

bool sum_func(int arr[], int k) {
    int len = *(&arr + 1) - arr;
    cout << len << endl;
    for(int i = 0; i < len-1;i++) {
        for(int j = i+1; j < len;j++) {
            if((arr[i] + arr[j]) == k){
                return true;
            }
        }
    }
    return false;
}
