import React from 'react'

const QuillHtmlRender = ({ content }) => {
  return (
    <div dangerouslySetInnerHTML={{__html: content}} />
  );
}

export default QuillHtmlRender;