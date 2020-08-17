
### Simple text edit component 

Built with [Reagent and Leiningen template](https://github.com/reagent-project/reagent)

Pick a color with color picker.
Edit text and drag and drop dots to change the text color. 
See it in action [here](https://alimdar.github.io/).
Its not much but its honest work. 

### Development mode
To start the Figwheel compiler run:

```
lein figwheel
```

Figwheel will automatically push cljs changes to the browser.
Once Figwheel starts up, you should be able to open the `public/index.html` page in the browser.

### REPL

The project is setup to start nREPL on port `7002` once Figwheel starts.
Once you connect to the nREPL, run `(cljs)` to switch to the ClojureScript REPL.
