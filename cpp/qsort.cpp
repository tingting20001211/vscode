#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void swap(vector<int> &array, int a, int b){
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
}

void print(vector<int> array){
    for(int i=0;i<array.size();i++)
        cout<<array[i]<<" ";
    cout<<endl;
}

void qsort(vector<int> &array, int low, int high){
    if(low>high)
        return ;
    int l=low, r=high, key=array[low];
    while(l<r){
        while(l<r && array[r]>=key)
            r--;
        swap(array, l, r);
        while(l<r && array[l]<=key)
            l++;
        swap(array, l, r);
    }
    array[l]=key;
    qsort(array,low,l-1);
    qsort(array,l+1,high);
}



int main(){   
    int n;
    cin>>n;
    vector<int> array(n,0);
    for(int i=0;i<n;i++)
        cin>>array[i];
    qsort(array, 0, n-1);
    print(array);
    return 0;
}



