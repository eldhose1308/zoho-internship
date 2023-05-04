/**
 * 12. Write a JavaScript program to construct the following pattern, using a nested for loop.
 *           * * * * *
 *           * * * *
 *           * * *
 *           * *
 *           *
 */
function patternStarInverted(limit) {
    let pattern = '';

    for (let i = 0; i < limit; i++) {
        pattern = ''
        for (let j = limit; j > i; j--)
            pattern += '* ';
        console.log(pattern);
    }
}

let limit = 5;
patternStarInverted(limit);