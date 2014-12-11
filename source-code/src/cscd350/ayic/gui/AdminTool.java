package cscd350.ayic.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cscd350.ayic.utility.DataBase;
import net.miginfocom.swing.MigLayout;

public class AdminTool extends JFrame
{
	protected JButton _add;
	protected JButton _remove;
	protected JLabel _disabledLabel;
	protected JLabel _enabledLabel;
	protected JList<String> _enabled;
	protected JList<String> _all;

	public AdminTool()
	{
		_add = new JButton("Add");
		_remove = new JButton("Remove");
		_disabledLabel = new JLabel("Disabled items:");
		_enabledLabel = new JLabel("Enabled items:");
		_enabled = new JList<String>(DataBase.getInstance().getEnabled());
		_all = new JList<String>(DataBase.getInstance().getDisabled());

		setLookAndFeel();
		initialize();
	}

	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}

	private void initialize()
	{
		setTitle("Admin tool");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel(new MigLayout());
		setContentPane(contentPane);

		_enabled.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		_enabled.setLayoutOrientation(JList.VERTICAL);
		_enabled.setVisibleRowCount(-1);
		_all.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		_all.setLayoutOrientation(JList.VERTICAL);
		_all.setVisibleRowCount(-1);
		JScrollPane left = new JScrollPane(_all);
		left.setPreferredSize(new Dimension(300, 400));
		JScrollPane right = new JScrollPane(_enabled);
		right.setPreferredSize(new Dimension(300, 400));

		_add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (String s : _all.getSelectedValuesList())
				{
					int id = Integer.parseInt(s.split(" ")[0]);
					DataBase.getInstance().enableAnswer(id);
				}

				_enabled.setModel(DataBase.getInstance().getEnabled());
				_all.setModel(DataBase.getInstance().getDisabled());
			}
		});

		_remove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (String s : _enabled.getSelectedValuesList())
				{
					int id = Integer.parseInt(s.split(" ")[0]);
					DataBase.getInstance().disableAnswer(id);
				}

				_enabled.setModel(DataBase.getInstance().getEnabled());
				_all.setModel(DataBase.getInstance().getDisabled());
			}
		});
		
		contentPane.add(_disabledLabel);
		contentPane.add(_enabledLabel, "wrap");
		contentPane.add(left);
		contentPane.add(right, "wrap");
		contentPane.add(_add);
		contentPane.add(_remove);
	}
}
