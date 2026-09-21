

- **UI Native Select Custom Styling**: Wrap <select> in div.custom-select-wrapper with relative positioning. Apply ppearance: none; to hide OS dropdown arrow. Use absolute positioned lucide-react ChevronDown on the right. Map to Vanilla CSS variables (e.g. var(--bg-input), var(--bg-card) for options, var(--text-main)) to fully support dark mode.
- **UI Data Sorting Standard**: Whenever displaying a list of entities (such as Employees) in a Dropdown (<select>) or a Data Table, ALWAYS sort the array alphabetically (A-Z) by their primary display name before rendering.

- **Mobile-Friendly UI Standards**:


- **UI Native Select Custom Styling**: Wrap <select> in div.custom-select-wrapper with relative positioning. Apply  ppearance: none; to hide OS dropdown arrow. Use absolute positioned lucide-react ChevronDown on the right. Map to Vanilla CSS variables (e.g. var(--bg-input), var(--bg-card) for options, var(--text-main)) to fully support dark mode.
- **UI Data Sorting Standard**: Whenever displaying a list of entities (such as Employees) in a Dropdown (<select>) or a Data Table, ALWAYS sort the array alphabetically (A-Z) by their primary display name before rendering.

- **Mobile-Friendly UI Standards**:
  1. **Dropdowns:** Prioritize clean strings (e.g., [Name] ([Last 4 chars of ID])) over long concatenations to prevent mobile UI overflow.
  2. **Data Tables:** Do NOT use card-morphing responsive tables unless requested. Instead, use .compact-mobile-table CSS class which drastically reduces ont-size and padding on mobile screens to fit more columns without horizontal scrolling.
-   * * U I   D a t a   P a g i n a t i o n   S t a n d a r d * * :   W h e n e v e r   r e n d e r i n g   d a t a   t a b l e s   w i t h   l a r g e   d a t a s e t s ,   A L W A Y S   i m p l e m e n t   a   n u m b e r e d   p a g i n a t i o n   c o m p o n e n t   a t   t h e   b o t t o m .  
     -   * * S t y l e * * :   U s e   a   c a r d - l i k e   f o o t e r   w i t h   \ l u c i d e - r e a c t \   C h e v r o n L e f t / R i g h t   i c o n s   f o r   n a v i g a t i o n ,   a n d   n u m b e r e d   p a g e   b u t t o n s .   M a p   t h e   a c t i v e   p a g e   b u t t o n   t o   \   a r ( - - a c c e n t - p r i m a r y ) \ .  
     -   * * S t a t e * * :   D e f a u l t   t o   2 5   i t e m s   p e r   p a g e   ( \ i t e m s P e r P a g e   =   2 5 \ ) .   C a l c u l a t e   \ 	 o t a l P a g e s \   a n d   s l i c e   t h e   d a t a s e t   a c c o r d i n g l y .  
     -   * * V a n i l l a   C S S * * :   D o   N O T   u s e   T a i l w i n d .   U s e   s t a n d a r d   i n l i n e   s t y l e s   o r   \ i n d e x . c s s \   c l a s s e s   f o r   t h e   p a g i n a t i o n   c o n t a i n e r   ( e . g .   \ d i s p l a y :   f l e x \ ,   \ g a p :   8 p x \ ) .  
 - **UI Data Table Container Standard**: Whenever creating a main page or tab that hosts a Data Table, ALWAYS maximize the container to the screen by removing surrounding padding (use `padding: 0`). Allow the table itself or the inner components to handle their own padding.
- **Video Splash Screen Standard**: When implementing an MP4 video as a Splash Screen, ALWAYS use the `<video>` tag with `autoPlay`, `muted`, and `playsInline` attributes to guarantee automatic playback on mobile devices (iOS Safari strictly requires `muted` and `playsInline`). Use the `onEnded` event listener to trigger the completion callback (`onAnimationComplete`), and apply `object-fit: contain` to preserve the video's aspect ratio without cropping.
- **Static Image Splash Screen Standard**: When a static image is requested for the Splash Screen, use an `<img>` tag with CSS keyframes to control the animation (e.g., `opacity` for fade effects). Rely on a `setTimeout` inside a `useEffect` hook to trigger the `onAnimationComplete` callback precisely when the CSS animation duration concludes. Always use `object-fit: contain` unless otherwise specified.
