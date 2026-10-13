import React from 'react';

export default function MobileTableViewport({
  children,
  className = '',
}) {
  return (
    <div className={`mobile-table-viewport ${className}`}>
      {children}
    </div>
  );
}
