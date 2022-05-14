import * as G from './global.js'

let chosenId = -1
const cmVw = document.getElementById('cmpVw'),
    ovrl = document.getElementById('overlay'),
    cmpVwExt = document.getElementById('cmpVwExt'),
    cmpVwFtBt = document.getElementById('cmpVwFtBt'),
    cmpVwPic = document.getElementById('cmpVwPic')

cmpVwFtBt.addEventListener('click', () =>
    G.request(G.ps, `/slc?id=${chosenId}`, () => G.redir(G.ndx)))

function openOrCloseDialog(a) {
    cmVw.style.display = a ? 'flex' : 'none'
    ovrl.style.display = a ? 'initial' : 'none'
}

window.onItemClick = function onItemClick(_id) {
    const id = parseInt(_id)

    chosenId = id
    cmpVwExt.addEventListener('click', () => openOrCloseDialog(false))

    G.request(G.gt, `/cmp?id=${id}`, (jsn) => {
        openOrCloseDialog(true)

        cmVw.querySelector('#cmpVwNm').textContent = jsn.name
        cmVw.querySelector('#cmpVwDsc').textContent = jsn.description
        cmpVwPic.src = `../../res_back/${jsn.image}.jpg`
    })
}
