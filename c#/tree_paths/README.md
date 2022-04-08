# Tree Paths

Given a list of paths in a file system:
```
["a", "a/b/c", "c", "c/d", "d", "c/d/e"]
```

Filter it in order to keep only the ones that allow us to describe the full file system.
```
["a/b/c", "d", "c/d/e"]
```

* "a" was excluded because it is covered by the path "a/b/c".
* "c" was excluded because it is covered by the path "c/d/e".
* "c/d" was excluded because it is covered by the path "c/d/e".

## Run tests

1. Go to the tests directory: `cd TreePaths/TreePathsTests/`
1. Run tests: `dotnet test`
