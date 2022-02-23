import Image from 'next/image'
import { useEffect, useMemo, useState, VFC } from 'react'
import { Block } from '../../types/BlockProviders'

const BlockIcon: VFC<Block & { size: number }> = ({ id, mod, size }) => {
   const defaultSrc = useMemo(() => `blocks/${mod ?? 'minecraft'}/${id}`, [id, mod])
   const [src, setSrc] = useState<string>()

   useEffect(() => setSrc(defaultSrc), [defaultSrc])

   return (
      <Image
         objectFit='contain'
         alt={id}
         src={`/${src}.png`}
         height={size}
         width={size}
         onError={() => setSrc('unknown_block')}
      />
   )
}

export default BlockIcon