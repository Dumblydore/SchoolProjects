'use strict';

var React = require('react/addons');

require('styles/Lab5.scss');

let suit = function (pos) {
    if (pos < 12) {
        return '\u2663';
    } else if (pos >= 12 && pos < 25) {
        return '\u2666';
    } else if (pos >= 25 && pos < 38) {
        return '\u2665';
    } else {
        return '\u2660';
    }
};

let rank = function (pos) {
    if (pos < 13) {
        return cardRank[pos];
    } else if (pos >= 13 && pos < 26) {
        return cardRank[pos - 13];
    } else if (pos >= 26 && pos < 39) {
        return cardRank[pos - 26];
    } else {
        return cardRank[pos - 39];
    }
};

let shuffle = function (cards) {
    var counter = cards.length, temp, index;
    while (counter > 0) {
        index = Math.floor(Math.random() * counter);
        counter--;
        temp = cards[counter];
        cards[counter] = cards[index];
        cards[index] = temp;
    }
    return cards;
};


var Lab5 = React.createClass({

    getInitialState: function () {
        let deck = function () {
            let cards = [];
            for (let i = 0; i < 52; i++) {
                cards.push(i);
            }
            return cards;
        }();
        return {
            deck: shuffle(deck),
            players: [
                {
                    name: 'North',
                    '\u2663': [],
                    '\u2666': [],
                    '\u2665': [],
                    '\u2660': []
                },
                {
                    name: 'West',
                    '\u2663': [],
                    '\u2666': [],
                    '\u2665': [],
                    '\u2660': []
                },
                {
                    name: 'South',
                    '\u2663': [],
                    '\u2666': [],
                    '\u2665': [],
                    '\u2660': []
                },
                {
                    name: 'East',
                    '\u2663': [],
                    '\u2666': [],
                    '\u2665': [],
                    '\u2660': []
                }
            ]
        };
    },

    deal: function() {
        this.state.players.map(player => {
            for (let i = 0; i < 13; i++) {
                let index = this.state.deck.pop();
                player[suit(index)].push(rank(index));
            }
        });
    },

    render: function () {
        this.deal();
        let items = [];
        this.state.players.map(player => {
            items.push(
                <div className={player.name.toLowerCase()}>
                    <p>{player.name}</p>

                    <p>Clubs: {player['\u2663'].map(card => {
                        return card + ' ';
                    })}</p>

                    <p>Diamonds: {player['\u2666'].map(card => {
                        return card + ' ';
                    })}</p>

                    <p>Hearts: {player['\u2665'].map(card => {
                        return card + ' ';
                    })}</p>

                    <p>Spades: {player['\u2660'].map(card => {
                        return card + ' ';
                    })}</p>
                </div>
            );
        });
        return (
            <div className="Lab5">
                <h1>Cards</h1>
                {items}
            </div>
        );
    }
});

let cardRank = [
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    '10',
    'J',
    'Q',
    'K',
    'A'
];


module.exports = Lab5;
