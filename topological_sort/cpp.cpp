#include<bits/stdc++.h>
using namespace std;
vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
       //reverse the graph from outgoing edges to incoming edges
       vector<vector<int>> adj(graph.size());
       for(int i=0;i<graph.size();i++){
           for(int j=0;j<graph[i].size();j++){
               adj[graph[i][j]].push_back(i);
           }
       }
       //find indegree

       vector<int> indegree(graph.size());

       for(int i=0;i<adj.size();i++){
           for(int j=0;j<adj[i].size();j++){
               indegree[adj[i][j]]++;
           }
       }
       //insert 0 indegree into queue

       queue<int> q;

       for(int i=0;i<indegree.size();i++){
           if(!indegree[i])
               q.push(i);
       }

       //topo sort
       vector<int> ans;
       while(!q.empty()){
           int front = q.front();
           q.pop();
           for(int j=0;j<adj[front].size();j++){
               indegree[adj[front][j]]--;
               if(!indegree[adj[front][j]]){
                   q.push(adj[front][j]);
               }
           }
       }
       for(int i=0;i<indegree.size();i++){
           if(!indegree[i]){
               ans.push_back(i);
           }
       }
       return ans;
   }

int main(){
   int n;
   cin>>n;
   vector<vector<int>> mat(n,vector<int>(n));
   for(int i=0;i<n;i++){
       for(int j=0;j<n;j++){
           int d;
           cin>>d;
           mat[i][j] = d;
       }
   }
  vector<vector<int>> graph;
   for(int i=0;i<n;i++){
       vector<int> d;
       for(int j=0;j<n;j++){
           if(mat[i][j]){
               d.push_back(j);
           }
       }
       graph.push_back(d);
   }
  
   vector<int> ans = eventualSafeNodes(graph);
   for(int i=0;i<ans.size();i++) cout<<ans[i]<<" ";

}
