/**
 * 9. Write a Javascript program counter (increment, decrement, reset option) with an object.
 */

let counterObj = {
    value: 10,
    increment() {
        this.value++;
    },
    decrement() {
        this.value--;
    },
    reset() {
        this.value = 0;
    },
}

console.log(counterObj.value);
counterObj.increment();
console.log(counterObj.value);
counterObj.decrement();
console.log(counterObj.value);
counterObj.reset();
console.log(counterObj.value);

