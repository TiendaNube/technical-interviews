def sieve(n):
    if n <= 1:
        return []
    candidates = list(range(2, n+1))
    for p in range(2, n + 1):
        if candidates[p-2] is None:
            continue
        for i in range(2*p, n+1, p):
            candidates[i-2] = None
    return [p for p in candidates if p is not None]
