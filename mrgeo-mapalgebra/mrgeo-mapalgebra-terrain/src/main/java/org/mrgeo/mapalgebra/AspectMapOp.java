package org.mrgeo.mapalgebra;


import org.mrgeo.mapalgebra.parser.ParserAdapter;
import org.mrgeo.mapalgebra.parser.ParserNode;
import org.mrgeo.mapreduce.formats.TileClusterInfo;
import org.mrgeo.opimage.AspectDescriptor;

import java.util.Vector;

public class AspectMapOp extends RenderedImageMapOp implements TileClusterInfoCalculator
{
  public static String[] register()
  {
    return new String[] { "aspect" };
  }

  public AspectMapOp()
  {
    _factory = new AspectDescriptor();
  }

  @Override
  public Vector<ParserNode> processChildren(final Vector<ParserNode> children, final ParserAdapter parser)
  {
    Vector<ParserNode> result = new Vector<ParserNode>();
    if (children.size() != 1)
    {
      throw new IllegalArgumentException("aspect takes one argument - single-band raster elevation");
    }
    result.add(children.get(0));
    return result;
  }

  @Override
  public TileClusterInfo calculateTileClusterInfo()
  {
    // Aspect uses HornNormalOpImage which needs access to the
    // eight pixels surrounding each pixel. This means we need
    // to get the eight surrounding tiles.
    return new TileClusterInfo(-1, -1, 3, 3, 1);
  }

  @Override
  public String toString()
  {
    return "aspect()";
  }
}