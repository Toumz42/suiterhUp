$(function ()
{
    $.post("/Application/getCurrentUser",function(ret)
    {
        if (ret.isError)
        {
            window.location='/login';
        }
        else
        {
            /*alert(ret.messageRetour);*/
            $("#hello").text(ret.messageRetour);
        }
    })

    var btn = document.querySelector('.nav__button'), container = document.querySelector('.container');
    btn.addEventListener('click', function () {
        this.classList.toggle('open');
        container.classList.toggle('active');
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

    (function (window, document) {

        var     layout   = document.getElementById('layout'),
            menu     = document.getElementById('menu'),
            menuLink = document.getElementById('button'),
            overlay = document.getElementById('overlay');

        function toggleClass(element, className) {
            var classes = element.className.split(/\s+/),
                length = classes.length,
                i = 0;

            for(; i < length; i++) {
                if (classes[i] === className) {
                    classes.splice(i, 1);
                    break;
                }
            }
            // The className is not found
            if (length === classes.length) {
                classes.push(className);
            }

            element.className = classes.join(' ');
        }

        menuLink.onclick = function (e) {
            var active = 'enable';

            e.preventDefault();
            toggleClass(menu, active);
            toggleClass(menuLink, active);
            toggleClass(overlay, active);
        };

    }(this, this.document));

});