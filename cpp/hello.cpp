#include <iostream>
#include <vector>
#include <string>
using namespace std;


void fun(string s){
    cout<<s<<endl;
}

int axb(int a, int b){
    int c = a*b;
    return c;
}


int main(){
    vector<int> v;
    v.push_back(10);
    v.push_back(100);
    v.push_back(1000);
    for(auto i : v){
        cout<<i<<endl;
    }

    cout<<axb(56, 32);
    string s="fuck fuck!!";
    s.append("fuck");
    cout << "hello" << endl;
    return 0;
}
