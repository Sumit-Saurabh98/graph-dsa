#include<bits/stdc++.h>
using namespace std;
map<int,unordered_set<int>>adj;
   int dir[4][2]={{1,0},{0,1},{-1,0},{0,-1}};
   void fill(int i,int j,vector<vector<char>>&grid){
       int n=grid.size(),m=grid[0].size();
       int u=i*m+j;


       for(int ind=0;ind<4;ind++){
           int a=i+dir[ind][0];
           int b=j+dir[ind][1];


           if(a>=0 and b>=0 and a<n and b<m and grid[a][b]==grid[i][j]){
               int v=a*m+b;


               adj[u].insert(v);
           }
       }


       return;
   }
    bool dfsCycle(int node,int par,bool viss[]){
       viss[node]=true;
      
       for(auto it:adj[node]){
           if(!viss[it]){
               if(dfsCycle(it,node,viss)) return true;
           }
           else if(viss[it] and it!=par) return true;
       }
       return false;
   }
   bool containsCycle(vector<vector<char>>& grid) {
       int n=grid.size(),m=grid[0].size();
       adj.clear();
      
       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               fill(i,j,grid);
           }
       }


       bool viss[n*m+1];
       memset(viss,false,sizeof(viss));


       for(int i=0;i<n*m;i++){
           if(!viss[i] and dfsCycle(i,-1,viss)) return true;
       }
       return false;
   }


int main(){
   int n;
   cin>>n;
   vector<vector<char>> grid(n, vector<char>(n));
   for(int i=0;i<n;i++){
       for(int j=0;j<n;j++){
           char ch;
           cin>>ch;
           grid[i][j] = ch;
       }
   }
   bool ans = containsCycle(grid);
   if(ans){
       cout<<"true";
   }
   else{
       cout<<"false";
   }
}
