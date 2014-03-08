---
layout: docs
title: Dynamic Programming
prev_section: configuration
next_section: posts
permalink: /docs/dynamic/
---

Dynamic programming is a very powerful algorithmic paradigm in which a problem is solved by identifying a 
collection of subproblems and tackling them one by one, smallest first, using the answers to small problems 
to help figure out larger ones, until the whole lot of them is solved. In dynamic programming we are not 
given a dag; the dag is implicit. Its nodes are the subproblems we define, and its edges are the 
dependencies between the subproblems: if to solve subproblem B we need the answer to subproblem A, then 
there is a (conceptual) edge from A to B. In this case, A is thought of as a smaller subproblem than B and 
it will always be smaller, in an obvious sense.
