/* *****************************************************************************
 *  Name:    Devin Plumb
 *  NetID:   dplumb
 *  Precept: P06
 *
 *  Partner Name:     N/A
 *  Partner NetID:    N/A
 *  Partner Precept:  N/A
 *
 *  Hours to complete assignment (optional): ~6
 *
 **************************************************************************** */

Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */

Deque:
    I created my own data structure for deque, but it is essentially a linked
    list, except it has pointers in both directions. I thought this would be
    easiest for popping items off of both the front and back of the list and
    maintaining connectedness. There was no reason to use a full array when I
    had no need to access items anywhere from 1 through n-2, and a linked list
    would have only had pointers going in one direction, which would have made
    it tedious to pop items off of the back.

Randomized Queue:
    I used a resizable array for randomized queue, because using a linked list
    or a similar data structure would have meant that a proper implementation of
    dequeue would have taken time at least linear in n. Instead, I used the
    algorithm taught in class for switching to a smaller/larger array (halve
    when 1/4 full, double when full). To uniformly randomly dequeue in constant
    time, I randomly generated the index of the item to dequeue, saved the item
    itself, and replaced it with the item at the end of the array, which I
    deleted. This guarantees adjacency of items in the array.

    I believe this algorithm was shown in class.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  48  bytes

    ignoring the memory used by the items themselves, we are left with constant
    memory usage

    Object overhead: 16 bytes

    array reference: 24

    int: 4

    padding: 4

Deque:              ~  24n  bytes

    Object overhead: 16 bytes

    reference to first: 8
    reference to last: 8

    each node:
        reference to previous: 8
        reference to item: 8
        reference to next: 8

    all nodes: 24n

    size: 4

    padding: 4

    approximation, not including underlying items: 24n + 40 = ~24n


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

    None.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */

    None.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

    N/A

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

    Nothing serious.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

    None.
