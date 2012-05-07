# Yet Another LISP-Like

Educational project at 3rd grade of Novosibirsk State University written in Java.

This is academic nondeterministic programming language with 2 non-deterministic operations:

	$ do

Compute something nondeterministic amount of times or fallback with default something

	$ any

Chose one of two arguments and compute it. 

You can implement your own functions, see src\ru\nsu\ccfit\kanterov\yall\functions.

## Types

Currently supported: integers, arrays of [T], arrays of arrays and arrays of arrays of arrays are supported too. 

To support **do** and **any** functions there is Undefinite[T1,T2... Tn] type, that reflects that evaluated expression could have one of listed types.

## TODO

There are lots of things to do.

Currently there is no ability to:

- declare function
- declare macros
- ... many other things

## Documentation

See doc/reference.pdf. Available only in Russian. Sorry.

## Build

Build using ant. See Build.xml, Build.sh

## Examples / Tests

Available in test/. 

There is bubble sort implemented in yall, see test/sort.yall.
