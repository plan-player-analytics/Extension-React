/*
    Copyright(c) 2021 AuroraLS3

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension;

import com.djrapitops.plan.extension.CallEvents;
import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.annotation.TableProvider;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import com.djrapitops.plan.extension.icon.Icon;
import com.djrapitops.plan.extension.table.Table;
import com.volmit.react.React;
import com.volmit.react.api.RAIEvent;
import primal.lang.collection.GList;

/**
 * DataExtension.
 *
 * @author AuroraLS3
 */
@PluginInfo(name = "React", iconName = "react", iconFamily = Family.BRAND, color = Color.LIGHT_BLUE)
public class ReactExtension implements DataExtension {

    public ReactExtension() {
    }

    @Override
    public CallEvents[] callExtensionMethodsOn() {
        return new CallEvents[]{CallEvents.SERVER_EXTENSION_REGISTER, CallEvents.SERVER_PERIODICAL};
    }

    @TableProvider
    public Table reactActions() {
        Table.Factory table = Table.builder()
                .columnOne("React Action", Icon.called("react").of(Family.BRAND).build());

        GList<RAIEvent> events = React.instance().getReact().raiController.getRai().getEvents();
        for (RAIEvent event : events) {
            String[] parameters = event.getPars();
            table.addRow(event.getType().formatFor(parameters.length, parameters));
        }
        return table.build();
    }
}