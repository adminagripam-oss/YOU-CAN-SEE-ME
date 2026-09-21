import React, { useEffect, useState } from 'react';
import './AnimatedSplash.css';

export default function AnimatedSplash({ isAppReady, onAnimationComplete }) {
  useEffect(() => {
    // Total Animation Duration: 4 seconds.
    // Adding 0.5s buffer before triggering the completion callback.
    const timer = setTimeout(() => {
       if (onAnimationComplete) {
         onAnimationComplete();
       }
    }, 4500);
    return () => clearTimeout(timer);
  }, [onAnimationComplete]);

  return (
    <div className={`agri-splash-container ${isAppReady ? 'fade-out' : ''}`}>
       <div className="agri-splash-content">
          <svg className="agri-splash-svg" viewBox="0 0 400 400" xmlns="http://www.w3.org/2000/svg">
             <defs>
               <filter id="glow-blue" x="-20%" y="-20%" width="140%" height="140%">
                 <feGaussianBlur stdDeviation="4" result="blur" />
                 <feComposite in="SourceGraphic" in2="blur" operator="over" />
               </filter>
               
               <linearGradient id="solid-face-grad" x1="0%" y1="0%" x2="100%" y2="100%">
                 <stop offset="0%" stopColor="rgba(56, 189, 248, 0.8)" />
                 <stop offset="100%" stopColor="rgba(255, 255, 255, 0.9)" />
               </linearGradient>
               
               <linearGradient id="leaf-grad" x1="0%" y1="100%" x2="0%" y2="0%">
                 <stop offset="0%" stopColor="#10b981" />
                 <stop offset="100%" stopColor="#047857" />
               </linearGradient>
             </defs>

             {/* Layer 3: Solid Face (Phase 4) */}
             <path className="agri-face-solid" 
                   d="M 180 100 
                      C 200 90, 220 120, 225 150 
                      C 230 160, 235 170, 230 180 
                      C 220 200, 240 220, 230 250 
                      C 220 270, 190 280, 170 270 
                      C 150 250, 140 200, 180 100 Z" 
                   fill="url(#solid-face-grad)" />

             {/* Layer 2: Agriculture Elements (Phase 3) */}
             <g className="agri-leaves">
               {/* Neck Leaves */}
               <path d="M 170 270 C 130 290, 100 230, 120 180 C 130 150, 150 120, 180 100 C 150 130, 140 180, 170 270 Z" fill="url(#leaf-grad)" />
               <path d="M 150 220 C 110 200, 90 150, 130 130 C 140 160, 140 190, 150 220 Z" fill="#34d399" />
               
               {/* Palm Crown */}
               <path d="M 180 100 C 150 70, 120 80, 100 110 C 130 90, 160 90, 180 100 Z" fill="#059669" />
               <path d="M 190 95 C 180 50, 150 40, 120 60 C 160 50, 180 60, 190 95 Z" fill="#10b981" />
               <path d="M 200 90 C 210 40, 240 30, 270 50 C 240 50, 220 60, 200 90 Z" fill="#047857" />
               <path d="M 210 95 C 240 60, 280 70, 300 100 C 260 80, 230 80, 210 95 Z" fill="#34d399" />
               <path d="M 195 90 C 190 30, 210 10, 230 0 C 210 20, 200 50, 195 90 Z" fill="#059669" />
             </g>

             {/* Layer 1: Face Mesh (Phase 2) */}
             <g className="agri-face-mesh" filter="url(#glow-blue)">
               {/* Connecting Lines */}
               <path d="M 180 100 L 225 150 L 230 180 L 230 250 L 170 270 Z" />
               <path d="M 180 100 L 200 150 L 190 200 L 170 270" />
               <path d="M 225 150 L 190 200 L 230 180" />
               <path d="M 190 200 L 230 250" />
               
               {/* Node Intersections */}
               <circle cx="180" cy="100" r="4" />
               <circle cx="225" cy="150" r="4" />
               <circle cx="230" cy="180" r="3" />
               <circle cx="230" cy="250" r="4" />
               <circle cx="170" cy="270" r="4" />
               <circle cx="200" cy="150" r="3" />
               <circle cx="190" cy="200" r="4" />
             </g>

             {/* Layer 4: Typography (Phase 5) */}
             <g className="agri-brand-text-group">
                <text x="200" y="340" className="agri-brand-text" textAnchor="middle">AgriFace</text>
             </g>
          </svg>
       </div>
    </div>
  );
}
