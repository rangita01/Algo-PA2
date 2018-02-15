from collections import defaultdict
from itertools import combinations
from ast import literal_eval

class Graph():
    def __init__(self):

        self.edges = defaultdict(list)
        self.weights = {}

    def add_edge(self, from_node, to_node, weight):
        # Note: assumes edges are bi-directional
        self.edges[from_node].append(to_node)
        self.edges[to_node].append(from_node)
        self.weights[(from_node, to_node)] = weight
        self.weights[(to_node, from_node)] = weight

graph = Graph()

f=open("C:/Users/Rangita/PycharmProjects/dijkstra/dijkstra.txt",'r')
edges=literal_eval(f.read())

nodes=set( [x[0] for x in edges] )

for edge in edges:
    graph.add_edge(*edge)

def dijsktra(graph, initial, end):
        # shortest paths is a dict of nodes
        # whose value is a tuple of (previous node, weight)
    shrtstPath = {initial: (None, 0)}
    currNode = initial
    visited = set()

    while currNode != end:
        visited.add(currNode)
        dns = graph.edges[currNode]
        wtCurrNode = shrtstPath[currNode][1]

        for nxtNode in dns:
            weight = graph.weights[(currNode, nxtNode)] + wtCurrNode
            if nxtNode not in shrtstPath:
                shrtstPath[nxtNode] = (currNode, weight)
            else:
                currShrtstWt = shrtstPath[nxtNode][1]
                if currShrtstWt > weight:
                    shrtstPath[nxtNode] = (currNode, weight)

        next_dns = {node: shrtstPath[node] for node in shrtstPath if node not in visited}
        if not next_dns:
            return "Route Not Possible"
        # next node is the destination with the lowest weight
        currNode = min(next_dns, key=lambda k: next_dns[k][1])

    # Work back through dns in shortest path
    path = []
    while currNode is not None:
        path.append(currNode)
        nxtNode = shrtstPath[currNode][0]
        currNode = nxtNode
    # Reverse path
    path = path[::-1]
    return path

x = list (combinations(nodes, 2))
res = []
d = {}
nu = set([x[0] for x in edges])
for i in nu:
    d[i] = {}
for i in edges:
    d[i[0]][i[1]]=i[2]
for i in nodes:
    print('Destination Node '+i+' path is:')
    res=dijsktra(graph,'A',i)
    cost = 0
    print(res)
    for j in range(len(res) - 1):
        cost += d[res[j]][res[j+1]]
    print('path value is: ', cost)





