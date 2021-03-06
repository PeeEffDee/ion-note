import React from 'react'
import dynamic from 'next/dynamic'

const QuillNoSSRWrapper = dynamic(import('react-quill'), {	
	ssr: false,
	loading: () => <p>Loading ...</p>,
})

const modules = {
  toolbar: [
    [{ header: '1' }, { header: '2' }, { font: [] }],
    [{ size: [] }],
    ['bold', 'italic', 'underline', 'strike', 'blockquote', 'code-block'],
    [
      { list: 'ordered' },
      { list: 'bullet' },
      { indent: '-1' },
      { indent: '+1' },
    ],
    ['link', 'image', 'video'],
    ['clean'],
  ],
  clipboard: {
    matchVisual: false, // toggle to add extra line breaks when pasting HTML
  },
}

const formats = [ /* Quill editor formats. See https://quilljs.com/docs/formats/ */
  'header',
  'font',
  'size',
  'bold',
  'italic',
  'underline',
  'strike',
  'blockquote',
  'list',
  'bullet',
  'indent',
  'link',
  'image',
  'video',
]
  
export default function QuillEditor() {
  return <QuillNoSSRWrapper 
    modules={modules} 
    formats={formats} 
    theme="snow"
    style={{height:'90vh', width:'100%'}}
  />
}