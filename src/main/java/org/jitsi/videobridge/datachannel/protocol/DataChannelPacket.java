/*
 * Copyright @ 2018 - present 8x8, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jitsi.videobridge.datachannel.protocol;

import org.jetbrains.annotations.*;
import org.jitsi.rtp.*;
import org.jitsi.rtp.extensions.*;

import java.nio.*;

public class DataChannelPacket extends Packet
{
    public final ByteBuffer data;
    public final int sid;
    public final int ppid;

    public DataChannelPacket(ByteBuffer data, int sid, int ppid)
    {
        this.data = data;
        this.sid = sid;
        this.ppid = ppid;
    }

    @Override
    public int getSizeBytes()
    {
        return data.limit();
    }

    @NotNull
    @Override
    public ByteBuffer getBuffer()
    {
        return data;
    }

    @Override
    public void serializeTo(@NotNull ByteBuffer byteBuffer)
    {
        data.rewind();
        byteBuffer.put(data);
        data.rewind();
    }

    @NotNull
    @Override
    public Packet clone()
    {
        return new DataChannelPacket(ByteBufferKt.clone(data), sid, ppid);
    }
}