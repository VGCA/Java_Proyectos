/**
 * MAIN BUTTONS
 */
let gameBoard = document.querySelector('.game_board')
let messageTurn = document.querySelector('.game_turn')
let endGame = document.querySelector('.endgame')
let endGameResult = document.querySelector('.endgame_result')
let buttonReset = document.querySelector('.endgame_button')

/**
 * VARIABLES
 */
let isTurnX = true
let turn = 0
let maxTurn = 9
let players = {
    x: "cross",
    o: "circle"
}
let winningPosition = [[0, 1, 2], [3, 4, 5], [6, 7, 8], [0, 3, 6], [1, 4, 7], [2, 5, 8], [0, 4, 8], [2, 4, 6]]

startGame()

function startGame() {
    createBoard()
    messageTurn.textContent = isTurnX ? "X" : "O"
    isTurnX = true
    turn = 0
    endGame.classList.remove('show')
}

function createBoard() {
    let cells = 9
    // Make again the boardgame
    while (gameBoard.firstElementChild) {
        gameBoard.firstElementChild.remove()
    }
    for (let i = 0; i < cells; i++) {
        let div = document.createElement('div')
        div.classList.add('cell')
        div.addEventListener('click', handleGame, { once: true })
        gameBoard.append(div)
    }
}

function handleGame(e) {
    let currentCell = e.currentTarget
    let currentTurn = isTurnX ? players.x : players.o
    turn++
    drawShape(currentCell, currentTurn)
    if (checkWinner(currentTurn)) {
        return
    }
    if (turn == maxTurn) {
        showEndGame(false)
    }

    changeTurn()
}

function drawShape(element, newClass) {
    element.classList.add(newClass)
}

function changeTurn() {
    isTurnX = !isTurnX
    messageTurn.textContent = isTurnX ? "X" : "O"
}

function checkWinner(currentPlayer) {
    let cells = document.querySelectorAll('.cell')
    let winner = winningPosition.some(array => {
        return array.every(position => {
            return cells[position].classList.contains(currentPlayer)
        })
    })
    if (!winner) {
        return
    }
    showEndGame(true)
    return true
}

function showEndGame(winner) {
    endGame.classList.add('show')
    if (winner) {
        endGameResult.textContent = `${isTurnX ? "X" : "O"} ha ganado el juego`
    } else {
        endGameResult.textContent = `El juego ha empatado`
    }
}

buttonReset.addEventListener('click', startGame)