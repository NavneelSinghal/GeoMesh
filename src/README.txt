Classes -

1. ArrList

    This class is for my own implementation of an ArrayList that I use.

    Data members : arr[] (the array to store elements) and idx (to store the size of the ArrList)

    Functions :
        1. ArrList() : The constructor, makes a new array of size 1 and set the size to 0.

        2. push_back() : Function to insert an element at the back of the ArrList, and for this we check if the size of the ArrList is equal to the size of the array, in which case, we make a new array of double the size and copy all elements in arr to it and set arr to be equal to that list. Then we put the new element in the correct place. The time complexity of this is amortized O(1) as discussed in class.

        3. get(i) : This function gets the element at position i in the ArrList. Time and space complexity is O(1).

        4. set(i, val): This function sets the element at position i in the ArrList to the specified val. Time and space complexity is O(1).

        5. size() : This function returns the number of elements in the ArrList. Time and space complexity is O(1).


2. OTriple

    This class is an implementation for an ordered triple of elements of the type T.

    Data members : x, y, z (the triple will represent the mathematical structure (x, y, z)).

    Functions :
        1. OTriple(a, b, c) : The constructor, makes a new ordered triple of a, b, c. Time and space complexity is O(1).

        2. compareTo(u): Compares this object to u with respect to lexicographical ordering. Time and space complexity is O(1).

3. Pair

    This class is an implementation of a two element set (an unordered pair).

    Data members : F, S (the first and the second elements of the pair).

    Functions :
        1. Pair(F, S) : Constructor of the class, makes the two element set using the elements F and S (in an order such that the first element is less than or equal to the second). Time and space complexity is O(1).

        2. compareTo(p) : This functions returns the result of the lexicographical comparison of this and p. Time and space complexity is O(1).

4. Queue

    This class is for my own implementation of a Queue. The implementation is more or less analogous to ArrList class in terms of expandability, except that we have two indices, one that stores the first and the last element in the queue circularly.

    Data members : arr[] (for storing the elements in the queue), first, last (indices of the first and the last element in the queue), size (the size of the Queue).

    Functions :
        1. enqueue(val) : Function to put an element in the Queue. If the queue is full, it expands the queue using the algorithm we described in the ArrList class, and sets the first and the last variables accordingly. Then it puts val at the correct position, and updates the last and the size variables. Time and space complexity is O(1).

        2. dequeue() : Function to remove the last element and return it. If the Queue is empty, it returns null, else saves the first element, makes the element at that place null and updates the first and the size and then returns the element we saved. Time and space complexity are O(1).

        3. isEmpty() : Returns true if the size is 0 and false otherwise. Time and space complexity is O(1).

        4. size() : Returns the size of the Queue. Time and space complexity O(1).

5. Stack

    This class is for my own implementation of a Stack. This class is almost the same as the ArrList class with the only difference being the presence of a pop() function which just returns the last element and before that it makes the last element of the array null and reduces the size of the array. The complexities are the same as ArrList, and the time and space complexity for the pop function is O(1) for both time and space.

6. UTriple

    This class is for representing a three element set (an unordered triple).

    Data members : x, y, z (the members of the triple)

    Functions :
        1. UTriple(x, y, z) : Constructor of the class, this sorts x, y, z using bubblesort on 3 elements and stores the values in the correct order. Time and space complexity is O(1).

        2. compareTo(u) : This functions returns the result of lexicographical comparison with u. Time and space complexity both are O(1).

7. RBTree

    This class is an implementation of a red black tree.

    Data : RedBlackNode<T,E> root (to store the root of the tree)

    Functions :

    isBlack(RedBlackNode<T,E> node):
        This returns false if the node's colour is red, and otherwise it returns true (because by default null nodes are black)

    RedBlackNode<T, E> search(T key):
        This returns the node whose key matches with the given key, if it is found, and a node with values equal to null otherwise. For this, we iterate starting from the root and use a loop that iterates while the current node n is not null, and at every step, does one of the following : 1. Go to the left child if the node's key is more than the mentioned key, 2. Go to the right child if the node's key is less than the mentioned key, and 3. break as we found the node. Now if the node n is not null, we simply return it, else we make a new node with null data in it and return it. The complexity of this is O(log(n)*time taken in comparison) where n is the number of nodes in the tree. This is because at every step, the level either increases or the algorithm stops, and the number of levels is bounded above by 2log(n+1). The space complexity is O(1) because we used a constant amount of space in this algorithm.

    RedBlackNode<T, E> search1(T key):
        This is a helping function for the next function, and does the same job as the previous function, but returns a null node if the key is not found. The time and space complexities stay the same as the previous function.

    insert(T key, E value):
        This function inserts the key and value as given into the red black tree.
        Firstly it checks if the key is already in the tree or not, if it is, it simply appends the value to the node's values list. Else, we know that the key is not in the tree, so we need to make a new node (which is by default red) and insert it into the tree. So for this, we first check if the root is null or not. If the root is null (id est the tree is empty), we assign to the root a new node with the key as given and values as a list containing the value as given, and return. If the root is not null, we perform an ordinary bst insertion initially, by keeping track of the parent and finding where to insert the node by going down the levels till we reach a null node, and finally assigning the node as the appropriate child of the parent. Now there might be a double red problem (that is, the parent and the node might both be red). So for fixing that, we make a loop for eliminating the error by propagating it upwards or ending it somewhere in between. While we don't reach the null node or the root, and while the colour of the node is red, we do this. If the node's parent is black (this parent must exist as the root is black and this node can't be the root), we stop the loop. Else we check if the uncle exists or not. If it exists and it is red, then we recolor the uncle, parent and the grandparent and set the node equal to the node's grandparent and continue with the loop. In the other case, we are guaranteed an uncle coloured black. In this case, we rotate it suitably in 4 cases (based on whether the parent is the left or right child of the grandparent, and the subcases of these being whether the node is the left or right child of the parent), using the functions leftRot and rightRot which have a constant time complexity. When we use rotations, in each case, the colour of the grandparent becomes black, so we perform at most 1 recolouring. Eventually this loop ends and we have only one property that might not be satisfied, that is the colour of the root must be black. So we always change the colour of the root to black. So this finally keeps all the properties of the red black tree intact and we have inserted the node successfully.
        For the space complexity, we have always used a constant number of variables and references in this function, so the space complexity is constant. For the time complexity, the loop runs for at most half the distance from the node to the root (because at every step we either terminate, or replace the node with the grandparent), and we always do a constant number of operations (excluding comparisons) in every loop. Since the distance is upper bounded by the depth of the tree, which is in turn upper bounded by 2 log(n+1) where n is the number of nodes, insertion takes place in O(log(n)*time taken in comparison) time.

    leftRot(RedBlackNode<T,E> node):
        This functions changes the structure of the subtree rooted at node as shown (it assumes that the right child, say r, of node exists)

                node                     r
               /    \                   / \
              a      r        ->     node  c
                    / \             /   \
                   b   c           a     b

        Here we do a restructuring using a constant number of pointer changes, and link r to the parent of the node if node is not the root, else makes r the root.
        The space and time complexities of this function are thus O(1) for both.

    rightRot(RedBlackNode<T,E> node):
        This function changes the structure of the subtree rooted at node in a similar but symmetric manner to the previous function. (It assumes that the left child exists).
        The space and time complexities of this functionn are also O(1) for both.

8. Point

    This class is to implement the point interface we were given.

    Data members : x, y, z (coordinates of the point), edges (an ArrList of edges that have one point equal to this point), triangles (an ArrList of triangles that have the point as one of the vertices)

    Functions :
        1. getX(), getY(), getZ() : These return the x, y and z coordinates of that point.

        2. getXYZcoordinate() : This returns an array of the three coordinates of that point. Time and space complexity : O(1).

        3. dist(a) : This returns the square of the Euclidean distance of this point from the point a. Time and space complexity O(1).

        4. compareTo(p) : This returns the result of the lexicographical comparison of this with the point p. Time and space complexity is O(1).

        5. cross(p) : This returns the cross product of this and p, and the time complexity and space complexity both are O(1).

        6. add(p) : This returns the vector sum of this and p, and the time and space complexity both are O(1).

        7. addTriangle(t) : This adds t to the ArrList triangles. Time and space complexity O(1).

        8. addEdge(e) : This adds e to the ArrList edges. Time and space complexity O(1).

        9. mag() : Returns the sum of the squares of the coordinates. Time and space complexity both O(1).

        10. dot(p) : Returns the square of the dot product of this and p. Time and space complexity O(1).

9. Edge

    This class is to implement the edge interface that we were given.

    Data members : Pair<Point> e (which contains the two end points of the edge), and triangles (ArrList which stores the triangles whose edge this is).

    Functions :
        1. Edge(a, b) : Constructor which constructs a new edge with points a and b, and initialises the triangles list to a new ArrList. Time and space complexity O(1).

        2. compareTo(e) : Returns the result of lexicographic comparison of this and e. Time and space complexity O(1)

        3. edgeEndPoints() : Returns an array of end points of the edge, time and space complexity O(1).

        4. length() : returns the squared length of the edge, time and space complexity O(1)

        5. toString() : returns the string representation, only for testing purposes

10. Triangle

    This class is to implement the triangle interface that we were given.

    Data members : Point a, b, c (to store points), Edge bc, ca, ab (to store edges), ArrList<Triangle> triangles (to store adjacent triangles), index (to store the order of insertion, used in Shape), var (a miscellaneous variable for help in bfs), static float eps (the threshold we will use for checking if the triangle is valid or not)

    Functions :
        1. Triangle(a, b, c, bc1, ca1, ab1) : Constructor with arguments equal to the three points and the three edges. Time and space complexity O(1) both.

        2. compareTo(arg0) : Returns the result of lexicographical (wrt points) comparison between this and arg0. Time and space complexity both O(1).

        3. triangle_coord() : returns an array of the three points in the triangle. Time and space complexity both O(1).

        4. addTriangle(t) : adds a triangle to the list of neighbours of this triangle. Time and space complexity both O(1).

        5. isnotvalid(p1, p2, p3) : this checks if the points are collinear or not. For that, it finds the square of the tangent of all the angles in the triangle, and if any of them is less than eps^2 (where eps is defined as 1e-3), then it says that the triangle is not valid. For finding the square of the tangent, we only need to find the square of the ratio between the magnitude of the cross and the dot product of the displacement vectors corresponding to the edges. TIme and space complexity are both O(1).

        6. diff(p1, p2) : Finds the difference of two points and returns a new point. Time and space complexity O(1).

11. Shape

    Data members : tr, ed, po (all red black trees to store the triangles, edges and points respectively), trlist, edlist, polist (all ArrLists to store the triangles, edges and the points in the order of insertion), edgedeg1, edgedeg2, edgedegmore (to store the number of edges with degree 1, 2 and more respectively).

    Functions :
        1. ADD_TRIANGLE : This function is used for insertion of a triangle. Firstly we check if the triangle is valid or not. If it is not, then we return false, else we check if the points already exist or need to be made (in which case we update the polist and po). We do the same thing with the edges. Then we create a new triangle (only if it is not already present) and add it to tr. We also update the data we store inside the edges and points and the triangle. Then we update edgedeg1,
        edgedeg2 and edgedegmore. The overall complexity becomes O(log(#triangles) + log(#points) + log(#edges)) which is O(log(#triangles)) as the number of points is <= 3*number of triangles and similar for edges. Space complexity is O(1).

        2. TYPE_MESH : This simply checks the number of edges which have 1, 2 or more triangle neighbours. If every edge has two neighbours then the mesh is of type 1. Else if there exists an edge which has more than two edges, then the mesh is of type 3. Else it is of type 2. The time and space complexity is O(1).

        3. lensort2 : This function uses mergesort for sorting the elements of an ArrList from the indices left to right. Time complexity is O(n log n) and the space complexity is O(n) where n = right - left + 1

        4. BOUNDARY_EDGES : This function finds the edges with only one triangle neighbour by traversing the list of all edges, and then sorts them according to their length and returns the sorted array of those edges. The time complexity is O(n log n + m) and the space complexity is O(n), where n is the number of edges which we will return and m is the total number of edges we have.

        5. dfs : This function performs an iterative dfs, starting from the root as specified in the arguments, changing the values of the array in the argument to the integer specified in the arguments whenever an index is visited. Time complexity is O(n) where n is the number of vertices in the component and space complexity is O(h) where h is theheight of the dfs tree formed.

        6. COUNT_CONNECTED_COMPONENTS : This function runs a dfs (as in the previous function) for all vertices, if they have not been visited, and increases the number of components by 1 whenever we find such a vertex. Then it returns the number of components. Time complexity is O(n) where n is the number of vertices (because we visit a vertex exactly a constant number of times). The space complexity is O(h) where h is the maximum depth of the dfs tree formed.

        7. NEIGHBORS_OF_TRIANGLE : This function returns a list of the triangle neighbours of the triangle, and this is simple because we only need to traverse the list of neighbours that we stored in the triangle. Time and space complexity both are O(log w + n) where n is the number of neighbours of the triangle and w is the number of triangles.

        8. EDGE_NEIGHBOR_TRIANGLE : This function returns a list of the edges of the triangle if it exists (search in rbtree), which takes up constant time and space. So  the time and space complexity of this function is O(log w) where w is the number of triangles.

        9. VERTEX_NEIGHBOR_TRIANGLE : This function returns a list of the vertices of the triangle if it exists. So the time and space complexity is O(log w) where w is the number of triangles.

        10. EXTENDED_NEIGHBOR_TRIANGLE : This function finds the extended neighbours of a triangle. For that, it merges the neighbours of a point on the basis of insertion time (I used a 3 array merge analogous to that in mergesort), and then removes duplicates and the original triangle. This is done in O(n + log w) time and O(n) space where n is the number of extended neighbours of the triangle (this is becaue any repeated entry can be repeated at most 3 times),
        and w is the number of triangles we have.

        11. INCIDENT_TRIANGLE : This returns an array of the triangles which have one point equal to the given point, which are already stored in the point. Time complexity is O(n + log w) and space complexity is O(n) where n is the number of triangles that are neighbours of this point, and w is the number of points we have.

        12. NEIGHBORS_OF_POINT : This returns an array of the points which are adjacent to the given point, which can be found from the edges stored in the point. Time complexity is O(n + log w) and space complexity is O(n) where n is the size of the answer, and w is the number of points we have.

        13. EDGE_NEIGHBORS_OF_POINT : This returns the edges which are incident to the given point, which are stored in the point. Time complexity is O(n+logw) and the space complexity is O(n) where n is the size of the answer and w is the number of points we have.

        14. FACE_NEIGHBORS_OF_POINT : This is identical to the INCIDENT_TRIANGLE query, and everything said there applies here too.

        15. IS_CONNECTED : This returns if the two triangles given are connected or not. For that, we search if the two triangles given exist or not. If they don't, we return false. Else we do a dfs starting from one of the triangles and then check if the second triangle has been visited or not. The time and space complexity are both O(n) where n is the number of triangles, because the search takes O(log n) time, the dfs take O(size of component of chosen triangle)
        time, and making the visited array takes O(n) time.

        16. TRIANGLE_NEIGHBOR_OF_EDGE : This returns an array of triangles which are incident to the edge. We can directly find this from the list of triangles we have in the edges. So the time complexity is O(log w + n) and the space complexity is O(n) where n is the size of the answer and w is the number of edges we have currently.

        17. MAXIMUM_DIAMETER : This finds the diameter of the largest component (wrt the number of triangles). For that, we initially run dfs on each unvisited triangle and then find the size of each component. Then we choose the largest component. Then for each triangle in that component, we run a bfs and find the maximum distance of any triangle from this triangle. We take the maximum of all such values found, which will be the diameter of this component, which was required. Time and
        space complexity both are O(n) where n is the number of triangles with us currently.

        18. CENTROID : This finds the centroids of all the components and returns them in the required order. For this, firstly it uses the method in the previous function to mark all the triangles with their component numbers and then stores all the triangles in the relevant component's ArrList. And then we do the following for every component : Make an RBTree of vertices which will serve as a check for inserted vertices. Iterate over the triangles in the component and then
        check whether the vertices have already been inserted in the rb tree or not, and if they aren't, then add them into the rb tree as well as the ArrList of points we need to find the centroid of. Then find the centroid of the ArrList of points and add it to an array that we will eventually need to sort and return. The time complexity is O(n log n) and space is O(n) where n is the number of triangles in this component, because the first part takes O(n) time and space, and the
        computation for the second part is as follows : For every component the time for the construction of the ArrList is O(r log r) where r is the number of triangles in that component and the finding of the centroid takes O(p) time where p is the number of points in the component. So the total time in the computation of a component is O(r log r) where r is the number of triangles in that component. Summing over all components, we can bound this by O(n log n) where n is the
        number of triangles. And in the end, the sorting takes O(w log w) time where w is the number of components, and thus the total time complexity is O(n log n), and the space is O(n), as claimed.

        19. CENTROID_OF_COMPONENT : This finds the point if it exists, and then performs the dfs on the component having the first face neighbour of this point to get the component. Then we do a similar thing as in the previous query, and return the centroid. The time taken is O(log r) for the first part where r is the number of points, O(n) in the second part for the dfs, where n is the number of triangles and O(n log n) in the last part. So the time complexity is overall O(n log n)
        where n is the number of triangles. The space taken is O(n).

        20. CLOSEST_COMPONENT : This firstly finds the points in every component, and for every pair of distinct components, finds all possible distances between the points in them, and updates the minimum so far. The first part is similar to the centroid query, whose time complexity was O(n log n) and space complexity was O(n) where n is the number of triangles. For the second part, we have O(n^2) pairs of points to check in the worst case, so the time complexity of the second part is
        O(n^2), and the space complexity of the second part is O(1). So the time complexity is O(n^2) and the space complexity is O(n).

        21. ptsort : This is an implementation of mergesort for the points, and the time and space complexities are O(n log n) and O(n) respectively, where n is rght - left + 1.

12. RedBlackNode

    The objects of this class are used as nodes in a red black tree.
