import { useEffect } from 'react';

export default function MatrixRain() {
  useEffect(() => {
    const container = document.createElement('div');
    container.className = 'matrix-container';
    document.body.appendChild(container);

    const columns = Math.floor(window.innerWidth / 20);
    
    for (let i = 0; i < columns; i++) {
      const column = document.createElement('div');
      column.className = 'matrix-column';
      column.style.left = (i * 20) + 'px';
      column.style.animationDelay = Math.random() * 5 + 's';
      column.style.animationDuration = (15 + Math.random() * 10) + 's';
      
      // Generate random numbers representing "Smurf" balance
      let text = '';
      for (let j = 0; j < 30; j++) {
        text += Math.floor(Math.random() * 10);
        if (j % 5 === 4) text += '\n';
      }
      column.textContent = text;
      
      container.appendChild(column);
    }

    return () => {
      container.remove();
    };
  }, []);

  return null;
}
