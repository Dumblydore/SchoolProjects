'use strict';

var React = require('react/addons');

require('styles/Lab4.scss');

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

var Lab4 = React.createClass({

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
                    hand: []
                },
                {
                    name: 'West',
                    hand: []
                },
                {
                    name: 'South',
                    hand: []
                },
                {
                    name: 'East',
                    hand: []
                }
            ]
        };
    },
    deal: function () {
        this.state.players.map(player => {
            for (let i = 0; i < 13; i++) {
                let cardNumber = this.state.deck.pop();
                player.hand.push(suit(cardNumber) + rank(cardNumber));
            }
        });
    },
    render: function () {
        this.deal();
        let items = [];
        this.state.players.map(player => {
            items.push(
                <p>{player.name}: {player.hand.map(card => {
                    return card + ' ';
                })}</p>
            );
        });
        return (
            <div className="Lab4">
                <h1>Permute</h1>
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


module.exports = Lab4;
