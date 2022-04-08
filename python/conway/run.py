# -*- coding: utf-8 -*-
import time
import os
from conway import GameOfLife

def main():
    gol = GameOfLife()

    while True:
        gol.next_generation()
        print_gol(gol);
        time.sleep(1);


def print_gol(gol):

    os.system('clear')
    print('Generation %d' % gol.generation)
    for x in range(gol.size):
        line = ''
        for y in range(gol.size):
            line += ' x ' if gol.is_alive(x, y) else '   '
        print(line)
    print('\nPress Ctrl-C to stop.')


if __name__ == "__main__":
    main()
