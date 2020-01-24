-- This file creates 1 client, 1 container, and 2 iterators. We first
-- assume that the Contianer has information we would like to iterate over.
-- We then call the necessary methods and iterate over the contents.

!create client:Client
!create container:Container
!insert (client,container) into client_container
!client.populateContainer()

!container.createIterator()
!create iter:Iterator
!insert (client,iter) into client_iterator

!client.traverse()
