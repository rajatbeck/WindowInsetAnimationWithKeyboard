# WindowInsetAnimationWithKeyboard
Sample application showing keyboard animation controls for android 11 in action

<img src="https://github.com/rajatbeck/WindowInsetAnimationWithKeyboard/blob/main/app/keyboard_animation.gif" width="200" height="400" />


<h4>Minimum Requirement</h4>

<ul>
<li>Compile SDK >= 30</li>
<li>AppCompat version >= 1.3.0-alpha02</li>
<li>Jetpack version also available (Androidx core version >= 1.5.0-alpha02)</li>
</ul>

<h4>Features provided</h4>

<ul>
<li>Check the visibility of the keyboard.</li>
<li>Control the visibility of different insets including keyboard.</li>
<li>Listening to window inset animation eg. keyboard hide and show.</li>
<li>Controlling window inset animations eg. make keyboard part of the scroll.</li>
</ul>
 * Except for the first feature, all the other are only available above SDK >= 30, and the feature to check the visibility and height of the keyboard has some caveats like works for API 23+ when using windowSoftInputMode adjustResize and API 14 above for adjustpan.



<h4>UseCase</h4>


Can be used in the layout where we have lots of text inputs with frequent keyboard interaction like chat and conversation applications.

<h4>Listed below are the functions needed to achieve the above effect.</h4>

<ul>
<li>The inset type taken into observation is ime().</li>
<li>Integrated window inset animation callback provides us with callbacks which include :</li>
<ol>
<li>onPrepare(...)</li>
<li>onStart(...)</li>
<li>onProgress(...)</li>
<li>onEnd(...)</li>
<ol>
</ul>
<ul>
<li>Window inset listener to record start and end snapshot of the inset</li>
</ul>



<h4>Challenges Faced</h4>
<ul>
<li>Ran into some issues with animation offset when set to soft input keyboard behavior type adjustpan.</li></ul>


<h4>Useful links</h4> 
<ul>
<li> https://medium.com/androiddevelopers/animating-your-keyboard-fb776a8fb66d </li>
<li> https://medium.com/androiddevelopers/animating-your-keyboard-reacting-to-inset-animations-839be3d4c31b </li>
<li> https://www.youtube.com/watch?v=acC7SR1EXsI&t=270s&ab_channel=AndroidDevelopers </li>
<li> https://github.com/android/user-interface-samples/tree/main/WindowInsetsAnimation </li>
</ul>

