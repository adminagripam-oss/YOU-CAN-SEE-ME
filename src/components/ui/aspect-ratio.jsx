import React from 'react';

export function AspectRatio({ ratio = 16 / 9, children, className = '', style = {}, ...props }) {
  return (
    <div
      className={`aspect-ratio-wrapper ${className}`}
      style={{
        position: 'relative',
        width: '100%',
        aspectRatio: `${ratio}`,
        overflow: 'hidden',
        ...style,
      }}
      {...props}
    >
      {children}
    </div>
  );
}

export default AspectRatio;
