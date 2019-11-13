/* global Dicoogle */

export default class MyPlugin {

    constructor() {
        // TODO initialize plugin here
    }

    /**
     * @param {DOMElement} parent
     * @param {DOMElement} slot
     */
    render(parent, slot) {
        // TODO mount a new web component here
        const div = document.createElement('div');

        const h1 = document.createElement('h1');
        h1.innerHTML = "Hello from Dicoogle!";

        const button = document.createElement('button');
        button.innerHTML = "Click me!";

        div.appendChild(h1);
        div.appendChild(button);

        parent.appendChild(div);

    }

}
