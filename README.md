# Palestine Map (Graph)
in this Java desktop project I have created Palestine Map.
I have implemented the classic Dijkstra’s shortest path algorithm and optimize it for maps. Made it find route information between two cities in Palestine. 
User may choose the city through mouse or List. The path would appear on the map. This projects includes 50 cities.

# Dijkstra’s algorithm
 Dijkstra’s algorithm is a classic solution to the shortest path problem on a weighted graph. The basic idea is not difficult to understand. We maintain, for every vertex in the graph, the length of the shortest known path from the source to that vertex, and we maintain these lengths in a priority queue. Initially, we put all the vertices on the queue with an artificially high priority and then assign priority 0.0 to the source. The algorithm proceeds by taking the lowest-priority vertex off the PQ, then checking all the vertices that can be reached from that vertex by one edge to see whether that edge gives a shorter path to the vertex from the source than the shortest previously-known path. If so, it lowers the priority to reflect this new information.

# Running the project 
In order to run the project, needs the JavaFx library to be build on the projecet path.  

# File format example
The input files of the program contains as many cities as the user want.

file format:
Cities:

![00](https://user-images.githubusercontent.com/65151701/157654582-28addf52-b20e-4d70-905e-30ace01f6bf0.png)

Adjacents:

![01](https://user-images.githubusercontent.com/65151701/157654605-f8031cf3-9153-4ec1-9e63-769147989bd5.png)


# Programms versions
javafx.version=17.0.1,  
Java.version=17.0.8

# Program screenshot:
Browse for the inputs file:

![1](https://user-images.githubusercontent.com/65151701/157654694-7a850f4b-2f67-4503-81e0-ca1194f047e0.png)

Ready to run: (read the file correctly)

![2](https://user-images.githubusercontent.com/65151701/157654747-9988fc30-0c7a-47ce-80a6-d22c2ca7e27c.png)

Map:

![3](https://user-images.githubusercontent.com/65151701/157654814-3192d9cb-80f9-4ccc-99a3-7e250f56fa07.png)

Get the shortest path using mouse (by click):

![4](https://user-images.githubusercontent.com/65151701/157654907-a504b5e0-678f-4ebe-9134-d66177a21822.png)

Get the shortest path using the list:

![5](https://user-images.githubusercontent.com/65151701/157654966-5ca290e8-b512-4f17-8fe9-417e86f50d81.png)
