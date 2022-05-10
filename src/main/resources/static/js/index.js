import * as G from './global.js'

function rqs(whr) { G.request(
    G.ps,
    whr,
    () => G.redir(G.ndx)
) }

window.logOut = function logOut() { rqs(G.lgo) }
window.onClear = function onClear() { rqs(G.clr) }
