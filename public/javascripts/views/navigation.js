/**
 * Created by Jonathan on 31/05/2016.
 */
$(function(){
    var btn = document.querySelector('.nav__button'), container = document.querySelector('.container'), msg = document.querySelector('#hello'),
    nav = document.querySelector('.navigation');
    btn.addEventListener('click', function () {
        this.classList.toggle('open');
        container.classList.toggle('active');
        msg.classList.toggle('transparent');
        nav.classList.toggle('ombre');
    });

    (function hamb() {

        'use strict';
        document.querySelector('.material-design-hamburger__icon').addEventListener(
            'click',
            function() {
                var child;


                child = this.childNodes[1].classList;

                if (child.contains('material-design-hamburger__icon--to-arrow')) {
                    child.remove('material-design-hamburger__icon--to-arrow');
                    child.add('material-design-hamburger__icon--from-arrow');
                } else {
                    child.remove('material-design-hamburger__icon--from-arrow');
                    child.add('material-design-hamburger__icon--to-arrow');
                }

            });

    })();

});